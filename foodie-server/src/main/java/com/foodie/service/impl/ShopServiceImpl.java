package com.foodie.service.impl;

import com.foodie.constant.StatusConstant;
import com.foodie.context.BaseContext;
import com.foodie.dto.ShopAddDTO;
import com.foodie.dto.ShopPageQueryDTO;
import com.foodie.entity.PageBean;
import com.foodie.entity.Shop;
import com.foodie.mapper.ShopMapper;
import com.foodie.service.ShopService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;

    //新增店铺
    @Override
    public void add(ShopAddDTO shopAddDTO) {
        Shop shop = new Shop();

        BeanUtils.copyProperties(shopAddDTO, shop);

        shop.setStatus(StatusConstant.DISABLED);
        shop.setCreateTime(LocalDateTime.now());
        shop.setUpdateTime(LocalDateTime.now());
        shop.setCreateUser(BaseContext.getCurrentId());
        shop.setUpdateUser(BaseContext.getCurrentId());

        shopMapper.add(shop);
    }

    //店铺分页查询
    @Override
    public PageBean pageQuery(ShopPageQueryDTO shopPageQueryDTO) {
        PageHelper.startPage(shopPageQueryDTO.getPageNum(), shopPageQueryDTO.getPageSize());

        Page<Shop> page = shopMapper.pageQuery(shopPageQueryDTO);

        return new PageBean(page.getTotal(), page.getResult());
    }

    //设置店铺状态
    @Override
    public void updateStatus(Integer status, Integer id) {
        shopMapper.updateStatus(status, id);
    }

    @Override
    public void delete(List<Integer> ids) {
        for (Integer id : ids) {
            shopMapper.delete(id);
        }
    }
}
