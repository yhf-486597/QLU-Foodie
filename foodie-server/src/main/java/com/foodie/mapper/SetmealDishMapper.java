package com.foodie.mapper;

import com.foodie.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    @Delete("delete from setmeal_dish where setmeal_id = #{setmealId}")
    void deleteBySetmealId(Integer setmealId);

    void insertList(List<SetmealDish> setmealDishes);

    @Select("select * from setmeal_dish where dish_id = #{dishId}")
    List<SetmealDish> selectByDishId(Integer dishId);

    @Select("select * from setmeal_dish where setmeal_id = #{setmealId}")
    List<SetmealDish> selectBySetmealId(Integer setmealId);
}
