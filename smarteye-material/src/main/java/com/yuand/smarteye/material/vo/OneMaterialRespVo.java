package com.yuand.smarteye.material.vo;

import lombok.Data;

@Data
public class OneMaterialRespVo {
    Long materialTypeId;
    String valueSelect;          //TODO 后期改为数组，支持输入多个种类值
    Integer onematerialCount;
    Integer levelNumb;     //这个字段没必要写这里，但是前端我又不会弄
    Integer qualityPeriod;  //这个字段没必要写这里，但是前端我又不会弄
    //返回前端时需要
    String shelfName;

}
