package com.foodie.service.impl;

import com.foodie.constant.MessageConstant;
import com.foodie.constant.StatusConstant;
import com.foodie.context.BaseContext;
import com.foodie.dto.SetmealDTO;
import com.foodie.dto.SetmealPageQueryDTO;
import com.foodie.entity.PageBean;
import com.foodie.entity.Setmeal;
import com.foodie.entity.SetmealDish;
import com.foodie.exception.DeletionNotAllowedException;
import com.foodie.exception.UpdateNotAllowedException;
import com.foodie.mapper.CategoryMapper;
import com.foodie.mapper.DishMapper;
import com.foodie.mapper.SetmealDishMapper;
import com.foodie.mapper.SetmealMapper;
import com.foodie.service.SetmealService;
import com.foodie.vo.SetmealVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    //修改套餐
    @Override
    public void update(SetmealDTO setmealDTO) {
        //判断是否含有停售中的菜品
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        for (SetmealDish setmealDish : setmealDishes) {
            if (dishMapper.selectById(setmealDish.getDishId()).getStatus() == StatusConstant.DISABLED){
                throw new DeletionNotAllowedException(MessageConstant.DISH_NOT_ON_SALE);
            }
        }

        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);

        setmeal.setUpdateTime(LocalDateTime.now());
        setmeal.setUpdateUser(BaseContext.getCurrentId());

        setmealMapper.update(setmeal);

        setmealDishMapper.deleteBySetmealId(setmeal.getId());

        if (!setmealDishes.isEmpty()) {
            setmealDishMapper.insertList(setmealDishes);
        }

    }

    //新增套餐
    @Override
    public void add(SetmealDTO setmealDTO) {
        //判断是否含有停售中的菜品
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        for (SetmealDish setmealDish : setmealDishes) {
            if (dishMapper.selectById(setmealDish.getDishId()).getStatus() == StatusConstant.DISABLED){
                throw new DeletionNotAllowedException(MessageConstant.DISH_NOT_ON_SALE);
            }
        }

        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);

        setmeal.setCreateTime(LocalDateTime.now());
        setmeal.setUpdateTime(LocalDateTime.now());
        setmeal.setCreateUser(BaseContext.getCurrentId());
        setmeal.setUpdateUser(BaseContext.getCurrentId());

        setmealMapper.insert(setmeal);

        if (!setmealDishes.isEmpty()) {
            for (SetmealDish setmealDish : setmealDishes) {
                setmealDish.setSetmealId(setmeal.getId());
            }
            setmealDishMapper.insertList(setmealDishes);
        }
    }

    //套餐分页查询
    @Override
    public PageBean pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPageNum(), setmealPageQueryDTO.getPageSize());

        Page<SetmealVO> page = setmealMapper.pageQuery(setmealPageQueryDTO);

        return new PageBean(page.getTotal(), page.getResult());
    }

    //套餐起售停售
    @Override
    public void updateStatus(Integer status, Integer id) {
        //起售套餐时，判断其中是否含停售的菜品
        if (status == StatusConstant.ENABLED){
            List<SetmealDish> setmealDishes = setmealDishMapper.selectBySetmealId(id);
            for (SetmealDish setmealDish : setmealDishes) {
                Integer dishId = setmealDish.getDishId();
                if (dishMapper.selectById(dishId).getStatus() == StatusConstant.DISABLED){
                    throw new UpdateNotAllowedException(MessageConstant.DISH_NOT_ON_SALE);
                }
            }
        }

        setmealMapper.updateStatus(status, id);
    }

    //批量删除套餐
    @Override
    public void delete(List<Integer> ids) {
        //判断套餐是否在起售中
        for (Integer id : ids) {
            if (setmealMapper.selectById(id).getStatus() == StatusConstant.ENABLED){
                throw new DeletionNotAllowedException(MessageConstant.SETMEAL_ON_SALE);
            }
        }

        for (Integer id : ids) {
            setmealMapper.deleteById(id);
            setmealDishMapper.deleteBySetmealId(id);
        }
    }

    //根据id查询套餐
    @Override
    public SetmealVO selectById(Integer id) {
        SetmealVO setmealVO = new SetmealVO();

        Setmeal setmeal = setmealMapper.selectById(id);
        BeanUtils.copyProperties(setmeal, setmealVO);

        String categoryName = categoryMapper.selectById(setmeal.getCategoryId()).getName();
        setmealVO.setCategoryName(categoryName);

        return setmealVO;
    }
}
