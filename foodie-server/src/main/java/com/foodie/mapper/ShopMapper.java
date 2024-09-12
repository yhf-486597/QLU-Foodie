package com.foodie.mapper;

import com.foodie.dto.ShopPageQueryDTO;
import com.foodie.entity.Shop;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ShopMapper {

    @Insert("insert into shop" +
            "(name, image, status, restaurant_number, create_time, update_time, create_user, update_user) " +
            "VALUES " +
            "(#{name}, #{image}, #{status}, #{restaurantNumber}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void add(Shop shop);

    Page<Shop> pageQuery(ShopPageQueryDTO shopPageQueryDTO);

    @Update("update shop set status = #{status} where id = #{id}")
    void updateStatus(Integer status, Integer id);

    @Delete("delete from shop where id = #{id}")
    void delete(Integer id);
}
