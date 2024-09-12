package com.foodie.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "套餐菜品关系实体类")
public class SetmealDish {

    private Integer id;

    private Integer setmealId;

    private Integer dishId;

    private String name;

    private BigDecimal price;

    private Integer copies;
}
