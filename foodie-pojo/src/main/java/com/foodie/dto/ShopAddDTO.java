package com.foodie.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "新增店铺时传递的数据模型")
public class ShopAddDTO implements Serializable {

    @Schema(description = "店铺名称")
    private String name;

    @Schema(description = "店铺图片路径")
    private String image;

    @Schema(description = "店铺所在餐厅")
    private Integer restaurantNumber;

}
