package com.foodie.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "套餐相关接口返回的数据模型")
public class SetmealVO {

    @Schema(description = "套餐id")
    private Integer id;

    @Schema(description = "店铺id")
    private Integer shopId;

    @Schema(description = "分类id")
    private Integer categoryId;

    @Schema(description = "分类名称")
    private String categoryName;

    @Schema(description = "套餐名称")
    private String name;

    @Schema(description = "套餐图片路径")
    private String image;

    @Schema(description = "套餐价格")
    private BigDecimal price;

    @Schema(description = "套餐状态，1起售中 2停售中")
    private Integer status;

    @Schema(description = "套餐描述")
    private String description;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
