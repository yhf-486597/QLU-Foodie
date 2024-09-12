package com.foodie.mapper;

import com.foodie.dto.AdministratorPageQueryDTO;
import com.foodie.entity.Administrator;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdministratorMapper {

    @Select("select * from administrator where username = #{username}")
    Administrator selectByUsername(String username);

    @Select("select * from administrator where id = #{id}")
    Administrator selectById(Integer id);

    @Insert("insert into administrator " +
            "(name, username, password, phone, sex, id_number, status, is_top, create_time, update_time, create_user, update_user) " +
            "values " +
            "(#{name}, #{username}, #{password}, #{phone}, #{sex}, #{idNumber}, #{status}, #{isTop}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void add(Administrator administrator);

    @Update("update administrator set password = #{newPassword} where id = #{id}")
    void editPassword(String newPassword, Integer id);

    @Update("update administrator set status = #{status} where id = #{id}")
    void updateStatus(Integer status, Integer id);

    void update(Administrator administrator);

    Page<Administrator> pageQuery(AdministratorPageQueryDTO administratorPageQueryDTO);

    @Delete("delete from administrator where id = #{id}")
    void deleteById(Integer id);
}
