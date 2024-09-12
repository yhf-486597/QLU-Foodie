package com.foodie.mapper;

import com.foodie.dto.SetmealPageQueryDTO;
import com.foodie.entity.Setmeal;
import com.foodie.vo.SetmealVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

@Mapper
public interface SetmealMapper {

    void update(Setmeal setmeal);

    void insert(Setmeal setmeal);

    Page<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    @Update("update setmeal set status = #{status} where id = #{id}")
    void updateStatus(Integer status, Integer id);

    @Select("select * from setmeal where id = #{id}")
    Setmeal selectById(Integer id);

    @Delete("delete from setmeal where id = #{id}")
    void deleteById(Integer id);
}
