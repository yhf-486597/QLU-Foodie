package com.foodie.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "骑手分页查询时传递的数据模型")
public class RiderPageQueryDTO implements Serializable {

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "页码")
    private Integer pageNum;

    @Schema(description = "当前页要展示的数据条数")
    private Integer pageSize;

}
