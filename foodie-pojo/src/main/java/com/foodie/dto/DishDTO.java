package com.foodie.dto;

import com.foodie.entity.DishFlavor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Schema(description = "修改菜品时传递的数据模型")
public class DishDTO implements Serializable {

    @Schema(description = "菜品id")
    private Integer id;

    @Schema(description = "店铺id")
    private Integer shopId;

    @Schema(description = "分类id")
    private Integer categoryId;

    @Schema(description = "菜品名称")
    private String name;

    @Schema(description = "菜品图片路径")
    private String image;

    @Schema(description = "菜品价格")
    private BigDecimal price;

    @Schema(description = "菜品状态，1起售中 2停售中")
    private Integer status;

    @Schema(description = "菜品描述")
    private String description;

    @Schema(description = "菜品口味")
    private List<DishFlavor> flavor;

}
