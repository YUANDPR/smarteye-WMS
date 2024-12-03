package com.yuand.smarteye.material.vo;

import com.yuand.smarteye.material.entity.MaterialTypeEntity;
import com.yuand.smarteye.material.entity.ShelfEntity;
import lombok.Data;

import java.util.List;

@Data
public class ShelfWithMaterialTypeVo extends ShelfEntity {
    private Long wlId;

    private List<MaterialTypeEntity> materialTypeEntities;
}
