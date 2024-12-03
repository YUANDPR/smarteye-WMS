package com.yuand.auth.controller;


import com.alibaba.fastjson.TypeReference;
import com.yuand.auth.exception.NoUUIDException;
import com.yuand.auth.feign.IntegrationFeignService;
import com.yuand.auth.feign.ThirdPartFeignService;
import com.yuand.auth.service.CaptchaService;
import com.yuand.auth.vo.UserLoginVo;
import com.yuand.auth.vo.UserRegistVo;
import com.yuand.common.constant.RedisConstant;
import com.yuand.common.exception.BizCodeEnum;
import com.yuand.common.to.EmployeeEntityTo;
import com.yuand.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 登录注册模块
 */
@Slf4j
@RestController
@RequestMapping("auth")
public class LoginController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    IntegrationFeignService integrationFeignService;
    @Autowired
    private ThirdPartFeignService thirdPartFeignService;
    @Autowired
    private CaptchaService captchaService;

    /**
     * 接收到一个手机号，在此处生成验证码和缓存，然后转给第三方服务让他给手机发验证按
     * 其中要实现：1.接口防刷
     * 2.60秒发送一次验证码
     */
    @ResponseBody
    @GetMapping("/sms/sendcode")
    public R sendCode(@RequestParam("phone") String phone) {
        // 接口防刷
        // 60秒发送一次验证码
        // 先从redis中拿取  (redis缓存 key→ sms:code:电话号 value → code)
        String redisCode = stringRedisTemplate.opsForValue().get(RedisConstant.SMS_CODE_CACHE_PREFIX + phone);
        // 如果不为空，返回错误信息
        if (!StringUtils.isEmpty(redisCode)) {
            long CuuTime = Long.parseLong(redisCode.split("_")[1]);
            // 防止同一个phone在60秒内发出多次验证吗
            // 当前系统事件减去之前验证码存入的事件 小于60000毫秒=60秒
            if (System.currentTimeMillis() - CuuTime < 60 * 1000) {
                return R.error(BizCodeEnum.SMS_CODE_EXCEPTION.getCode(), BizCodeEnum.SMS_CODE_EXCEPTION.getMsg());
            }
        }

        // 生成6位数验证码
        Random random = new Random();
        String code = String.valueOf(100000 + random.nextInt(900000));

        // 缓存验证码,10分钟有效
        // TODO 缓存成功加入，而短信发送异常未成功，导致用户没收到短信，60s内也无法再次发送。怎么解决？
        String redis_code = code + "_" + System.currentTimeMillis();
        stringRedisTemplate.opsForValue().set(RedisConstant.SMS_CODE_CACHE_PREFIX + phone, redis_code, 10, TimeUnit.MINUTES);

        try {// 调用第三方短信服务
            return thirdPartFeignService.sendCode(phone, code);
        } catch (Exception e) {
            log.warn("短信服务远程调用错误");
        }
        return R.ok();
    }

    /**
     * @param vo                 数据传输对象
     * @param result             校验结果
     * @param redirectAttributes 数据重定向
     * @return
     */
    @PostMapping("/regist")
    public String regist(@Valid UserRegistVo vo, BindingResult result,
                         RedirectAttributes redirectAttributes) {   //RedirectAttributes可以通过session保存信息并在重定向的时候携带过去
        // 校验是否通过
        if (result.hasErrors()) {
            // 拿到错误信息放入 Map
            Map<String, String> errors = new HashMap<>();
            //如果校验不通过，则封装校验结果
            result.getFieldErrors().forEach(item -> {
                // 获取错误的属性名和错误信息
                errors.put(item.getField(), item.getDefaultMessage());
            });
            // 将错误信息放到session中
            redirectAttributes.addFlashAttribute("errors", errors);
            // 校验出错，重定向到注册页
            return "redirect:http://auth.gulimall.com/reg.html";
        }

        // 将传递过来的验证码与 存在redis中的验证码进行比较
        String code = vo.getCode();
        String s = stringRedisTemplate.opsForValue().get(RedisConstant.SMS_CODE_CACHE_PREFIX + vo.getPhone());
        if (!StringUtils.isEmpty(s)) {
            // 验证码和redis中的一致
            if (code.equals(s.split("_")[0])) {
                // 删除验证码：令牌机制
                stringRedisTemplate.delete(RedisConstant.SMS_CODE_CACHE_PREFIX + vo.getPhone());
                // 调用远程服务，真正注册
                R r = integrationFeignService.regist(vo);
                if (r.getCode() == 0) {
                    // 远程调用注册服务成功
                    return "redirect:http://auth.gulimall.com/login.html";
                } else {
                    Map<String, String> errors = new HashMap<>();
                    errors.put("msg", r.getData("msg", new TypeReference<String>() {
                    }));
                    redirectAttributes.addFlashAttribute("errors", errors);
                    return "redirect:http://auth.gulimall.com/reg.html";
                }
            } else {
                Map<String, String> errors = new HashMap<>();
                errors.put("code", "验证码错误");
                redirectAttributes.addFlashAttribute("errors", errors);
                // 校验出错，转发到注册页
                return "redirect:http://auth.gulimall.com/reg.html";
            }
        } else {
            Map<String, String> errors = new HashMap<>();
            errors.put("code", "验证码失效");
            redirectAttributes.addFlashAttribute("errors", errors);
            // 校验出错，转发到注册页
            return "redirect:http://smarteye.com/reg.html";
        }
    }

    @RequestMapping("/login") // 记得加requestbody，不然post请求参数封装不到vo中
    public R login(@RequestBody UserLoginVo vo, HttpServletResponse response, HttpSession session) {
        // 验证验证码
        boolean captcha = captchaService.validate(vo.getUuid(), vo.getCaptcha());
        if (!captcha) {
            return R.error(BizCodeEnum.LOGIN_CODE_EXCEPTION.getCode(), BizCodeEnum.LOGIN_CODE_EXCEPTION.getMsg());
        }
        // 远程服务
        R r = integrationFeignService.login(vo);

        if (r.getCode() == 0) {
            EmployeeEntityTo data = r.getData("data", new TypeReference<EmployeeEntityTo>() {
            });

//			String uuid = UUID.randomUUID().toString().replace("_", "");
            session.setAttribute("loginkey", data);
//			Cookie cookie = new Cookie("loginkey",uuid);
//			response.addCookie(cookie);
//			return "redirect:http://smarteye.com/";
            return R.ok();
        } else {// 登录失败重回登录页面，携带错误信息
            String msg = (String) r.get("msg");
//			Map<String, String> errors = new HashMap<>();
//			errors.put("msg", msg);
//			attributes.addFlashAttribute("errors", errors);
//			return "redirect:http://auth.smarteye.com/login.html";
            return R.error(BizCodeEnum.LOGIN_FAIL.getCode(), msg);
        }
    }

    @RequestMapping("/loginout")
    public String outlogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:http://auth.gulimall.com/login.html";
    }

    /**
     * 验证码
     */
    @GetMapping("/captcha")
    public void captcha(HttpServletResponse response, String uuid) throws NoUUIDException, IOException {
        //response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //获取图片验证码
        BufferedImage image = captchaService.getCaptcha(uuid);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        //关闭流
        IOUtils.closeQuietly(out);
    }

}
