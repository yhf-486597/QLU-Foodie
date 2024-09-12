package com.foodie.service.impl;

import com.foodie.constant.MessageConstant;
import com.foodie.constant.PasswordConstant;
import com.foodie.constant.PermissionConstant;
import com.foodie.constant.StatusConstant;
import com.foodie.context.BaseContext;
import com.foodie.dto.*;
import com.foodie.entity.Administrator;
import com.foodie.entity.PageBean;
import com.foodie.exception.*;
import com.foodie.mapper.AdministratorMapper;
import com.foodie.service.AdministratorService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorMapper administratorMapper;

    //管理员登录
    @Override
    public Administrator login(AdministratorLoginDTO administratorLoginDTO) {
        String username = administratorLoginDTO.getUsername();
        String password = administratorLoginDTO.getPassword();

        Administrator administrator = administratorMapper.selectByUsername(username);

        if (administrator == null){
            //用户不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(administrator.getPassword())){
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (administrator.getStatus() == StatusConstant.DISABLED){
            //账号被锁定（禁用）
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        return administrator;
    }

    //根据id查询管理员
    @Override
    public Administrator selectById(Integer id) {
        Administrator administrator = administratorMapper.selectById(id);

        return administrator;
    }

    //新增管理员
    @Override
    public void add(AdministratorAddDTO administratorAddDTO) {
        //判断是否有权限
        Integer permission = administratorMapper.selectById(BaseContext.getCurrentId()).getIsTop();
        if (permission == PermissionConstant.NOT_TOP){
            throw new NoPermissionException(MessageConstant.NO_PERMISSION);
        }

        Administrator administrator = new Administrator();

        BeanUtils.copyProperties(administratorAddDTO, administrator);

        administrator.setPassword(PasswordConstant.DEFAULT_PASSWORD);
        administrator.setStatus(StatusConstant.DISABLED);
        administrator.setIsTop(PermissionConstant.DEFAULT_PERMISSION);
        administrator.setCreateTime(LocalDateTime.now());
        administrator.setUpdateTime(LocalDateTime.now());
        administrator.setCreateUser(BaseContext.getCurrentId());
        administrator.setUpdateUser(BaseContext.getCurrentId());

        administratorMapper.add(administrator);
    }

    //管理员修改密码
    @Override
    public void editPassword(AdministratorEditPasswordDTO administratorEditPasswordDTO) {
        String oldPassword = administratorEditPasswordDTO.getOldPassword();
        String newPassword = administratorEditPasswordDTO.getNewPassword();
        String password = administratorMapper.selectById(BaseContext.getCurrentId()).getPassword();

        //判断旧密码是否正确
        if (!oldPassword.equals(password)){
            throw new PasswordErrorException(MessageConstant.OLD_PASSWORD_ERROR);
        }

        //判断新密码与原密码是否一致
        if (oldPassword.equals(newPassword)){
            throw new PasswordErrorException(MessageConstant.OLD_EQUALS_NEW);
        }

        administratorMapper.editPassword(newPassword, BaseContext.getCurrentId());
    }

    //启用禁用管理员
    @Override
    public void updateStatus(Integer status, Integer id) {
        //判断是否有权限
        Integer permission = administratorMapper.selectById(BaseContext.getCurrentId()).getIsTop();
        if (permission == PermissionConstant.NOT_TOP){
            throw new NoPermissionException(MessageConstant.NO_PERMISSION);
        }

        administratorMapper.updateStatus(status, id);

    }

    //编辑管理员信息
    @Override
    public void update(AdministratorEditDTO administratorEditDTO) {
        //判断是否有权限
        Integer permission = administratorMapper.selectById(BaseContext.getCurrentId()).getIsTop();
        if (permission == PermissionConstant.NOT_TOP){
            throw new NoPermissionException(MessageConstant.NO_PERMISSION);
        }

        Administrator administrator = new Administrator();
        BeanUtils.copyProperties(administratorEditDTO, administrator);

        administrator.setUpdateTime(LocalDateTime.now());
        administrator.setUpdateUser(BaseContext.getCurrentId());

        administratorMapper.update(administrator);
    }

    //管理员分页查询
    @Override
    public PageBean pageQuery(AdministratorPageQueryDTO administratorPageQueryDTO) {
        //设置默认值
        if (administratorPageQueryDTO.getPageNum() == null) {
            administratorPageQueryDTO.setPageNum(1);
        }
        if (administratorPageQueryDTO.getPageSize() == null) {
            administratorPageQueryDTO.setPageSize(5);
        }

        //开始分页查询
        PageHelper.startPage(administratorPageQueryDTO.getPageNum(), administratorPageQueryDTO.getPageSize());

        //获取Page集合
        Page<Administrator> page = administratorMapper.pageQuery(administratorPageQueryDTO);

        //返回结果
        return new PageBean(page.getTotal(), page.getResult());
    }

    //删除管理员
    @Override
    public void delete(List<Integer> ids) {
        //判断是否有权限
        Integer permission = administratorMapper.selectById(BaseContext.getCurrentId()).getIsTop();
        if (permission == PermissionConstant.NOT_TOP){
            throw new NoPermissionException(MessageConstant.NO_PERMISSION);
        }
        //判断要删除的管理员的情况是否满足删除条件
        for (Integer id : ids) {
            Administrator administrator = administratorMapper.selectById(id);
            if (administrator.getStatus() == StatusConstant.ENABLED){
                throw new DeletionNotAllowedException(MessageConstant.ADMINISTRATOR_ON_WORK);
            }
            if (administrator.getIsTop() == PermissionConstant.IS_TOP){
                throw new DeletionNotAllowedException(MessageConstant.ADMINISTRATOR_IS_TOP);
            }
        }

        for (Integer id : ids) {
            administratorMapper.deleteById(id);
        }
    }

}
