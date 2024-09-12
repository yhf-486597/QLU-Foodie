package com.foodie.service.impl;

import com.foodie.constant.MessageConstant;
import com.foodie.constant.StatusConstant;
import com.foodie.context.BaseContext;
import com.foodie.dto.DishDTO;
import com.foodie.dto.DishPageQueryDTO;
import com.foodie.entity.Dish;
import com.foodie.entity.DishFlavor;
import com.foodie.entity.PageBean;
import com.foodie.entity.SetmealDish;
import com.foodie.exception.DeletionNotAllowedException;
import com.foodie.mapper.*;
import com.foodie.service.DishService;
import com.foodie.vo.DishVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;
    @Autowired
    private SetmealMapper setmealMapper;

    //修改菜品
    @Override
    public void update(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        dish.setCreateTime(LocalDateTime.now());
        dish.setUpdateTime(LocalDateTime.now());
        dish.setCreateUser(BaseContext.getCurrentId());
        dish.setUpdateUser(BaseContext.getCurrentId());

        dishMapper.update(dish);

        List<DishFlavor> dishFlavors = dishDTO.getFlavor();

        dishFlavorMapper.deleteByDishId(dishDTO.getId());

        if (dishFlavors != null && !dishFlavors.isEmpty()) {
            for (DishFlavor dishFlavor : dishFlavors) {
                dishFlavor.setDishId(dish.getId());
            }
            dishFlavorMapper.insert(dishFlavors);
        }
    }

    //批量删除菜品
    @Override
    public void delete(List<Integer> ids) {
        //判断是否有起售中的菜品
        for (Integer id : ids) {
            Integer status = dishMapper.selectById(id).getStatus();
            if (status == StatusConstant.ENABLED){
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }

        //判断是否有绑定的套餐
        for (Integer id : ids) {
            List<SetmealDish> setmealDishes = setmealDishMapper.selectByDishId(id);
            if (!setmealDishes.isEmpty()) {
                throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
            }
        }

        for (Integer id : ids) {
            dishMapper.deleteById(id);
            dishFlavorMapper.deleteByDishId(id);
        }

    }

    //新增菜品
    @Override
    public void add(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        dish.setCreateTime(LocalDateTime.now());
        dish.setUpdateTime(LocalDateTime.now());
        dish.setCreateUser(BaseContext.getCurrentId());
        dish.setUpdateUser(BaseContext.getCurrentId());

        dishMapper.insert(dish);

        List<DishFlavor> dishFlavors = dishDTO.getFlavor();

        if (dishFlavors != null && !dishFlavors.isEmpty()) {
            for (DishFlavor dishFlavor : dishFlavors) {
                dishFlavor.setDishId(dish.getId());
            }
            dishFlavorMapper.insert(dishFlavors);
        }
    }

    //根据id查询菜品
    @Override
    public DishVO selectById(Integer id) {
        DishVO dishVO = new DishVO();

        Dish dish = dishMapper.selectById(id);
        BeanUtils.copyProperties(dish, dishVO);

        String categoryName = categoryMapper.selectById(dish.getCategoryId()).getName();
        dishVO.setCategoryName(categoryName);

        List<DishFlavor> flavor = dishFlavorMapper.selectByDishId(id);
        dishVO.setFlavor(flavor);

        return dishVO;
    }

    //根据分类id查询菜品
    @Override
    public List<Dish> selectByCategoryId(Integer categoryId) {
        return dishMapper.selectByCategoryId(categoryId);
    }

    //菜品分页查询
    @Override
    public PageBean pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPageNum(), dishPageQueryDTO.getPageSize());

        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);

        return new PageBean(page.getTotal(), page.getResult());
    }

    //菜品起售停售
    @Override
    public void updateStatus(Integer status, Integer id) {
        //停售菜品时，判断其有没有关联套餐，若有，一并停售
        if (status == StatusConstant.DISABLED){
            List<SetmealDish> setmealDishes = setmealDishMapper.selectByDishId(id);
            for (SetmealDish setmealDish : setmealDishes) {
                Integer setmealId = setmealDish.getSetmealId();
                setmealMapper.updateStatus(StatusConstant.DISABLED, setmealId);
            }
        }

        dishMapper.updateStatus(status, id);
    }
}
