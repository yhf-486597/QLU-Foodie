package com.foodie.mapper;

import com.foodie.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    @Delete("delete from dish_flavor where dish_id = #{dishId}")
    void deleteByDishId(Integer dishId);

    void insert(List<DishFlavor> dishFlavors);

    @Select("select * from dish_flavor where dish_id = #{dishId}")
    List<DishFlavor> selectByDishId(Integer dishId);
}
