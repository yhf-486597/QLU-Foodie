package com.foodie.service.impl;

import com.foodie.constant.StatusConstant;
import com.foodie.context.BaseContext;
import com.foodie.dto.CategoryAddDTO;
import com.foodie.dto.CategoryChangeDTO;
import com.foodie.dto.CategoryPageQueryDTO;
import com.foodie.entity.Category;
import com.foodie.entity.PageBean;
import com.foodie.mapper.CategoryMapper;
import com.foodie.service.CategoryService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    //修改分类
    @Override
    public void updateCategory(CategoryChangeDTO categoryChangeDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryChangeDTO, category);

        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(BaseContext.getCurrentId());

        categoryMapper.updateCategory(category);
    }

    //分类分页查询
    @Override
    public PageBean pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getPageNum(), categoryPageQueryDTO.getPageSize());

        Page<Category> page = categoryMapper.pageQuery(categoryPageQueryDTO);

        return new PageBean(page.getTotal(), page.getResult());
    }

    //启用、禁用分类
    @Override
    public void updateStatus(Integer status, Integer id) {
        categoryMapper.updateStatus(status, id);
    }

    //新增分类
    @Override
    public void add(CategoryAddDTO categoryAddDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryAddDTO, category);

        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setStatus(StatusConstant.DISABLED);
        category.setCreateUser(BaseContext.getCurrentId());
        category.setUpdateUser(BaseContext.getCurrentId());

        categoryMapper.add(category);
    }

    //根据id删除分类
    @Override
    public void delete(Integer id) {
        categoryMapper.delete(id);
    }

    //根据类型查询分类
    @Override
    public List<Category> selectByType(Integer type) {
        return categoryMapper.selectByType(type);
    }
}
