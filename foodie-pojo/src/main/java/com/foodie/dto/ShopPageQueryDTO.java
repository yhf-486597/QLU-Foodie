package com.foodie.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "店铺分页查询时传递的数据模型")
public class ShopPageQueryDTO implements Serializable {

    @Schema(description = "店铺名称")
    private String name;

    @Schema(description = "店铺所在餐厅")
    private String restaurantNumber;

    @Schema(description = "页码")
    private Integer pageNum;

    @Schema(description = "当前页要展示的数据条数")
    private Integer pageSize;

}
