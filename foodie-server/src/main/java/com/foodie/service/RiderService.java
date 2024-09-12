package com.foodie.service;

import com.foodie.dto.RiderApplyDTO;
import com.foodie.dto.RiderPageQueryDTO;
import com.foodie.entity.PageBean;
import com.foodie.entity.Rider;

import java.util.List;

public interface RiderService {

    //骑手分页查询
    PageBean pageQuery(RiderPageQueryDTO riderPageQueryDTO);

    //删除骑手
    void delete(List<Integer> ids);

    //根据id查询骑手
    Rider selectById(Integer id);

    //审核骑手申请
    void review(RiderApplyDTO riderApplyDTO);

    //启用禁用骑手
    void updateStatus(Integer status, Integer id);
}
