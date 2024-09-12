package com.foodie.service;

import com.foodie.dto.ShopAddDTO;
import com.foodie.dto.ShopPageQueryDTO;
import com.foodie.entity.PageBean;

import java.util.List;

public interface ShopService {

    //新增店铺
    void add(ShopAddDTO shopAddDTO);

    //店铺分页查询
    PageBean pageQuery(ShopPageQueryDTO shopPageQueryDTO);

    //设置店铺状态
    void updateStatus(Integer status, Integer id);

    //删除店铺
    void delete(List<Integer> ids);
}
