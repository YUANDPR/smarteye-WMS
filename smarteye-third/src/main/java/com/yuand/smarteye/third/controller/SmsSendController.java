package com.yuand.smarteye.third.controller;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.yuand.common.exception.BizCodeEnum;
import com.yuand.common.utils.R;
import com.yuand.smarteye.third.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Validated
@Controller
@RequestMapping("/sms")
public class SmsSendController {

    private static final Logger logger = LoggerFactory.getLogger(SmsSendController.class);

    @Autowired
    private SmsService smsService;

    @GetMapping("/sendcode")
    @ResponseBody
    public R sendCode(@Pattern(regexp = "^1[3-9]\\d{9}$", message = "无效的电话号码") @RequestParam("phone") String phone,
                      @Size(min = 6, max = 6, message = "验证码长度必须为6位") @RequestParam("code") String code) {
        try {
            smsService.send(phone, code);
            return R.ok();
        } catch (Exception exception) {
            logger.error("发送验证码失败: {}", exception.getMessage(), exception);
            return R.error(BizCodeEnum.SMS_SEND_CODE_EXCEPTION.getCode(), BizCodeEnum.SMS_SEND_CODE_EXCEPTION.getMsg());
        }
    }

    @GetMapping("/sendwarninfo")
    @ResponseBody
    public R sendWarnInfo(@RequestParam("phones") List<@Pattern(regexp = "^1[3-9]\\d{9}$", message = "无效的电话号码") String> phones,
                          @RequestParam("msg") String msg) {
        List<String> unSends = new ArrayList<>();
        for (String phone : phones) {
            try {
                smsService.sendWarnInfo(phone, msg);
            } catch (Exception exception) {
                logger.error("发送预警信息失败: {}", exception.getMessage(), exception);
                unSends.add(phone);
            }
        }
        if (unSends.isEmpty()) {
            return R.ok();
        } else {
            return R.error(100111, "部分号码发送失败").put("data", unSends);
        }
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R handleException(Exception ex) {
        logger.error("全局异常处理: {}", ex.getMessage(), ex);
        return R.error(BizCodeEnum.UNKNOWN_EXCEPTION.getCode(), BizCodeEnum.UNKNOWN_EXCEPTION.getMsg());
    }
}
