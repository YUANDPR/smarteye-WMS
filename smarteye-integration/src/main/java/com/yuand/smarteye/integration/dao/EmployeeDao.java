package com.yuand.smarteye.integration.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuand.smarteye.integration.entity.EmployeeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 员工表
 */
@Mapper
public interface EmployeeDao extends BaseMapper<EmployeeEntity> {

}
