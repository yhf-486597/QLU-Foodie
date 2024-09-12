package com.foodie.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(defaultValue = "套餐分页查询时传递的数据模型")
public class SetmealPageQueryDTO implements Serializable {

    @Schema(description = "套餐名称")
    private String name;

    @Schema(description = "店铺id")
    private Integer shopId;

    @Schema(description = "分类id")
    private Integer categoryId;

    @Schema(description = "套餐状态")
    private Integer status;

    @Schema(description = "页码")
    private Integer pageNum;

    @Schema(description = "当前页需展示的数据条数")
    private Integer pageSize;
}
