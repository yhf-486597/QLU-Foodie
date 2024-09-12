package com.foodie.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "菜品分页查询时传递的数据模型")
public class DishPageQueryDTO implements Serializable {

    @Schema(description = "分类id")
    private Integer categoryId;

    @Schema(description = "菜品名称")
    private String name;

    @Schema(description = "菜品状态，1起售中 0停售中")
    private Integer status;

    @Schema(description = "页码")
    private Integer pageNum;

    @Schema(description = "当前页需展示的数据条数")
    private Integer pageSize;
}
