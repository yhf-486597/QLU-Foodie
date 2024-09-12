package com.foodie.vo;

import com.foodie.entity.DishFlavor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "菜品相关接口返回的数据模型")
public class DishVO {

    @Schema(description = "菜品id")
    private Integer id;

    @Schema(description = "店铺id")
    private Integer shopId;

    @Schema(description = "分类id")
    private Integer categoryId;

    @Schema(description = "分类名称")
    private String categoryName;

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

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
