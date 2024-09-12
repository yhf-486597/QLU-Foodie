package com.foodie.service;

import com.foodie.dto.DishDTO;
import com.foodie.dto.DishPageQueryDTO;
import com.foodie.entity.Dish;
import com.foodie.entity.PageBean;
import com.foodie.vo.DishVO;

import java.util.List;

public interface DishService {

    //修改菜品
    void update(DishDTO dishDTO);

    //批量删除菜品
    void delete(List<Integer> ids);

    //新增菜品
    void add(DishDTO dishDTO);

    //根据id查询菜品
    DishVO selectById(Integer id);

    //根据分类id查询菜品
    List<Dish> selectByCategoryId(Integer categoryId);

    //菜品分页查询
    PageBean pageQuery(DishPageQueryDTO dishPageQueryDTO);

    //菜品起售停售
    void updateStatus(Integer status, Integer id);
}
