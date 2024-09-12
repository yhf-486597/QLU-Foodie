package com.foodie.service;

import com.foodie.dto.CategoryAddDTO;
import com.foodie.dto.CategoryChangeDTO;
import com.foodie.dto.CategoryPageQueryDTO;
import com.foodie.entity.Category;
import com.foodie.entity.PageBean;

import java.util.List;

public interface CategoryService {

    //修改分类
    void updateCategory(CategoryChangeDTO categoryChangeDTO);

    //分类分页查询
    PageBean pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    //启用禁用分类
    void updateStatus(Integer status, Integer id);

    //新增分类
    void add(CategoryAddDTO categoryAddDTO);

    //根据id删除分类
    void delete(Integer id);

    //根据类型查询分类
    List<Category> selectByType(Integer type);
}
