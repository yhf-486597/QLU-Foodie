package com.foodie.service;

import com.foodie.dto.*;
import com.foodie.entity.Administrator;
import com.foodie.entity.PageBean;

import java.util.List;

public interface AdministratorService {

    //管理员登录
    Administrator login(AdministratorLoginDTO administratorLoginDTO);

    //根据id查询管理员
    Administrator selectById(Integer id);

    //新增管理员
    void add(AdministratorAddDTO administratorAddDTO);

    //管理员修改密码
    void editPassword(AdministratorEditPasswordDTO administratorEditPasswordDTO);

    //启用禁用管理员
    void updateStatus(Integer status, Integer id);

    //编辑管理员信息
    void update(AdministratorEditDTO administratorEditDTO);

    //管理员分页查询
    PageBean pageQuery(AdministratorPageQueryDTO administratorPageQueryDTO);

    //删除管理员
    void delete(List<Integer> ids);
}
