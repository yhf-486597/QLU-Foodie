package com.foodie.mapper;

import com.foodie.dto.DishPageQueryDTO;
import com.foodie.entity.Dish;
import com.foodie.vo.DishVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DishMapper {

    void update(Dish dish);

    @Select("select * from dish where id = #{id}")
    Dish selectById(Integer id);

    @Delete("delete from dish where id = #{id}")
    void deleteById(Integer id);

    void insert(Dish dish);

    @Select("select * from dish where category_id = #{categoryId}")
    List<Dish> selectByCategoryId(Integer categoryId);

    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    @Update("update dish set status = #{status} where id = #{id}")
    void updateStatus(Integer status, Integer id);
}
