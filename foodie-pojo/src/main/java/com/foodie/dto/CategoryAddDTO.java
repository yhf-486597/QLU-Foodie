package com.foodie.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "新增分类时传递的数据模型")
public class CategoryAddDTO implements Serializable {

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "分类类型，1菜品分类 2套餐分类")
    private Integer type;

}
