package com.foodie.controller.admin;

import com.foodie.dto.DishDTO;
import com.foodie.dto.DishPageQueryDTO;
import com.foodie.entity.Dish;
import com.foodie.entity.PageBean;
import com.foodie.result.Result;
import com.foodie.service.DishService;
import com.foodie.vo.DishVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/dish")
@Tag(name = "菜品相关接口")
public class DishController {

    @Autowired
    private DishService dishService;

    @PutMapping
    @Operation(summary = "修改菜品")
    public Result update(@RequestBody DishDTO dishDTO) {
        log.info("修改菜品：{}", dishDTO);

        dishService.update(dishDTO);

        return Result.success();
    }

    @DeleteMapping
    @Operation(summary = "批量删除菜品")
    public Result delete(@RequestParam List<Integer> ids){
        log.info("批量删除菜品：{}", ids);

        dishService.delete(ids);

        return Result.success();
    }

    @PostMapping
    @Operation(summary = "新增菜品")
    public Result add(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品：{}", dishDTO);

        dishService.add(dishDTO);

        return Result.success();
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据id查询菜品")
    public Result<DishVO> selectById(@PathVariable Integer id) {
        log.info("根据id查询菜品：{}", id);

        DishVO dishVO = dishService.selectById(id);

        return Result.success(dishVO);
    }

    @GetMapping("/list")
    @Operation(summary = "根据分类id查询菜品")
    public Result<List<Dish>> selectByCategoryId(@RequestParam Integer categoryId) {
        log.info("根据分类id查询菜品：{}", categoryId);

        List<Dish> list = dishService.selectByCategoryId(categoryId);

        return Result.success(list);
    }

    @GetMapping("/page")
    @Operation(summary = "菜品分页查询")
    public Result<PageBean> pageQuery(@RequestParam(required = false) Integer categoryId,
                                      @RequestParam(required = false) String name,
                                      @RequestParam(required = false) Integer status,
                                      @RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "10") Integer pageSize) {

        DishPageQueryDTO dishPageQueryDTO = new DishPageQueryDTO();
        dishPageQueryDTO.setCategoryId(categoryId);
        dishPageQueryDTO.setName(name);
        dishPageQueryDTO.setStatus(status);
        dishPageQueryDTO.setPageNum(pageNum);
        dishPageQueryDTO.setPageSize(pageSize);

        log.info("菜品分页查询：{}", dishPageQueryDTO);

        PageBean pageBean = dishService.pageQuery(dishPageQueryDTO);

        return Result.success(pageBean);
    }

    @PostMapping("/status/{status}")
    @Operation(summary = "菜品起售停售")
    public Result updateStatus(@PathVariable Integer status, Integer id) {
        log.info("菜品起售停售：{}，菜品id：{}", status, id);

        dishService.updateStatus(status, id);

        return Result.success();
    }
}
