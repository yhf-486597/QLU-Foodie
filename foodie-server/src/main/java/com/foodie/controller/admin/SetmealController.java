package com.foodie.controller.admin;

import com.foodie.dto.SetmealDTO;
import com.foodie.dto.SetmealPageQueryDTO;
import com.foodie.entity.PageBean;
import com.foodie.result.Result;
import com.foodie.service.SetmealService;
import com.foodie.vo.SetmealVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
@Tag(name = "套餐相关接口")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    @PutMapping
    @Operation(summary = "修改套餐")
    public Result update(@RequestBody SetmealDTO setmealDTO) {
        log.info("修改菜品：{}", setmealDTO);

        setmealService.update(setmealDTO);

        return Result.success();
    }

    @PostMapping
    @Operation(summary = "新增套餐")
    public Result add(@RequestBody SetmealDTO setmealDTO) {
        log.info("新增菜品：{}", setmealDTO);

        setmealService.add(setmealDTO);

        return Result.success();
    }

    @GetMapping("/page")
    @Operation(summary = "套餐分页查询")
    public Result<PageBean> pageQuery(@RequestParam(required = false) String name,
                                      @RequestParam(required = false) Integer shopId,
                                      @RequestParam(required = false) Integer categoryId,
                                      @RequestParam(required = false) Integer status,
                                      @RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "5") Integer pageSize) {

        SetmealPageQueryDTO setmealPageQueryDTO = new SetmealPageQueryDTO();
        setmealPageQueryDTO.setName(name);
        setmealPageQueryDTO.setShopId(shopId);
        setmealPageQueryDTO.setCategoryId(categoryId);
        setmealPageQueryDTO.setStatus(status);
        setmealPageQueryDTO.setPageNum(pageNum);
        setmealPageQueryDTO.setPageSize(pageSize);

        log.info("套餐分页查询：{}", setmealPageQueryDTO);

        PageBean pageBean = setmealService.pageQuery(setmealPageQueryDTO);

        return Result.success(pageBean);
    }

    @PostMapping("/status/{status}")
    @Operation(summary = "套餐起售停售")
    public Result updateStatus(@PathVariable Integer status, Integer id) {
        log.info("套餐起售停售：{}，套餐id：{}", status, id);

        setmealService.updateStatus(status, id);

        return Result.success();
    }

    @DeleteMapping
    @Operation(summary = "批量删除套餐")
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("批量删除套餐：{}", ids);

        setmealService.delete(ids);

        return Result.success();
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据id查询套餐")
    public Result<SetmealVO> selectById(@PathVariable Integer id) {
        log.info("根据id查询套餐：{}", id);

        SetmealVO setmealVO = setmealService.selectById(id);

        return Result.success(setmealVO);
    }
}
