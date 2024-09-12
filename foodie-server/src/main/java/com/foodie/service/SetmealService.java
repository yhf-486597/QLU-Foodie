package com.foodie.service;

import com.foodie.dto.SetmealDTO;
import com.foodie.dto.SetmealPageQueryDTO;
import com.foodie.entity.PageBean;
import com.foodie.vo.SetmealVO;

import java.util.List;

public interface SetmealService {

    //修改套餐
    void update(SetmealDTO setmealDTO);

    //新增套餐
    void add(SetmealDTO setmealDTO);

    //套餐分页查询
    PageBean pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    //套餐起售停售
    void updateStatus(Integer status, Integer id);

    //批量删除套餐
    void delete(List<Integer> ids);

    //根据id查询套餐
    SetmealVO selectById(Integer id);
}
