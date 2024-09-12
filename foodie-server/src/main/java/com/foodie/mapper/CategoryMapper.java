package com.foodie.mapper;

import com.foodie.dto.CategoryPageQueryDTO;
import com.foodie.entity.Category;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    void updateCategory(Category category);

    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    @Update("update category set status = #{status} where id = #{id}")
    void updateStatus(Integer status, Integer id);

    @Insert("insert into category" +
            "(type, name, sort, status, create_time, update_time, create_user, update_user) " +
            "values " +
            "(#{type}, #{name}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void add(Category category);

    @Delete("delete from category where id = #{id}")
    void delete(Integer id);

    List<Category> selectByType(Integer type);

    @Select("select * from category where id = #{id}")
    Category selectById(Integer id);
}
