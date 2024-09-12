package com.foodie.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "封装分页查询结果的实体类")
public class PageBean {

    @Schema(description = "总记录数")
    private Long total;

    @Schema(description = "数据列表")
    private List result;

}
