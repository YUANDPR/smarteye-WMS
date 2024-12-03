package com.yuand.smarteye.material.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuand.common.utils.PageUtils;
import com.yuand.smarteye.material.entity.AskmbillEntity;

import java.util.Map;

/**
 * 物料申请单详情
 *
 * @author ${author}
 * @email ${email}
 * @date 2022-09-05 19:05:28
 */
public interface AskmbillService extends IService<AskmbillEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

