package com.foodie.dto;

import com.foodie.entity.SetmealDish;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Schema(description = "修改套餐时传递的数据模型")
public class SetmealDTO implements Serializable {

    @Schema(description = "套餐id")
    private Integer id;

    @Schema(description = "店铺id")
    private Integer shopId;

    @Schema(description = "分类id")
    private Integer categoryId;

    @Schema(description = "套餐名称")
    private String name;

    @Schema(description = "套餐图片路径")
    private String image;

    @Schema(description = "套餐价格")
    private BigDecimal price;

    @Schema(description = "套餐状态")
    private Integer status;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "关联菜品列表")
    private List<SetmealDish> setmealDishes;
}
