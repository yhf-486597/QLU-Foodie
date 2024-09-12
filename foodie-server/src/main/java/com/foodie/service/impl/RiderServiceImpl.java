package com.foodie.service.impl;

import com.foodie.constant.MessageConstant;
import com.foodie.constant.ReviewStatusConstant;
import com.foodie.constant.StatusConstant;
import com.foodie.context.BaseContext;
import com.foodie.dto.RiderApplyDTO;
import com.foodie.dto.RiderPageQueryDTO;
import com.foodie.entity.PageBean;
import com.foodie.entity.Rider;
import com.foodie.exception.DeletionNotAllowedException;
import com.foodie.mapper.RiderMapper;
import com.foodie.service.RiderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RiderServiceImpl implements RiderService {

    @Autowired
    private RiderMapper riderMapper;

    //骑手分页查询
    @Override
    public PageBean pageQuery(RiderPageQueryDTO riderPageQueryDTO) {
        //设置默认值
        if (riderPageQueryDTO.getPageNum() == null){
            riderPageQueryDTO.setPageNum(1);
        }
        if (riderPageQueryDTO.getPageSize() == null){
            riderPageQueryDTO.setPageSize(5);
        }

        //开启分页查询
        PageHelper.startPage(riderPageQueryDTO.getPageNum(), riderPageQueryDTO.getPageSize());

        //获取Page集合
        Page<Rider> page = riderMapper.pageQuery(riderPageQueryDTO);

        //返回结果
        return new PageBean(page.getTotal(), page.getResult());
    }

    //删除骑手
    @Override
    public void delete(List<Integer> ids) {
        //判断能否删除
        for (Integer id : ids) {
            Rider rider = riderMapper.selectById(id);
            if (rider.getStatus() == StatusConstant.ENABLED){
                throw new DeletionNotAllowedException(MessageConstant.RIDER_ON_WORK);
            }
        }

        for (Integer id : ids) {
            riderMapper.deleteById(id);
        }
    }

    //根据id查询骑手
    @Override
    public Rider selectById(Integer id) {
        return riderMapper.selectById(id);
    }

    //审核骑手申请
    @Override
    public void review(RiderApplyDTO riderApplyDTO) {
        if (riderApplyDTO.getStatus() == ReviewStatusConstant.UNREVIEWED){
            return;
        }

        riderMapper.review(riderApplyDTO);

        if (riderApplyDTO.getStatus() == ReviewStatusConstant.REVIEW_PASS){
            Rider rider = new Rider();
            BeanUtils.copyProperties(riderApplyDTO, rider);
            rider.setCreateTime(LocalDateTime.now());
            rider.setUpdateTime(LocalDateTime.now());
            rider.setCreateUser(BaseContext.getCurrentId());
            rider.setUpdateUser(BaseContext.getCurrentId());

            riderMapper.insert(rider);
        }
    }

    //启用禁用骑手
    @Override
    public void updateStatus(Integer status, Integer id) {
        riderMapper.updateStatus(status, id);
    }
}
