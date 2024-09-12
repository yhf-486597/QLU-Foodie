package com.foodie.mapper;

import com.foodie.dto.RiderApplyDTO;
import com.foodie.dto.RiderPageQueryDTO;
import com.foodie.entity.Rider;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RiderMapper {

    Page<Rider> pageQuery(RiderPageQueryDTO riderPageQueryDTO);

    @Select("select * from rider where id = #{id}")
    Rider selectById(Integer id);

    @Delete("delete from rider where id = #{id}")
    void deleteById(Integer id);

    @Update("update rider_apply set status = #{status} where id = #{id}")
    void review(RiderApplyDTO riderApplyDTO);

    @Insert("insert into rider" +
            "(name, phone, sex, id_number, status, create_time, update_time, create_user, update_user) " +
            "VALUES " +
            "(#{name}, #{phone}, #{sex}, #{idNumber}, #{status},#{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void insert(Rider rider);

    @Update("update rider set status = #{status} where id = #{id}")
    void updateStatus(Integer status, Integer id);
}
