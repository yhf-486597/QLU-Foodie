package com.foodie.controller.admin;

import com.foodie.dto.CategoryAddDTO;
import com.foodie.dto.CategoryChangeDTO;
import com.foodie.dto.CategoryPageQueryDTO;
import com.foodie.entity.Category;
import com.foodie.entity.PageBean;
import com.foodie.result.Result;
import com.foodie.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@Slf4j
@Tag(name = "分类相关接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PutMapping
    @Operation(summary = "修改分类")
    public Result updateCategory(@RequestBody CategoryChangeDTO categoryChangeDTO) {
        log.info("修改分类：{}", categoryChangeDTO);

        categoryService.updateCategory(categoryChangeDTO);

        return Result.success();
    }

    @GetMapping("/page")
    @Operation(summary = "分类分页查询")
    public Result<PageBean> pageQuery(@RequestParam(required = false) String name,
                                      @RequestParam(required = false) Integer type,
                                      @RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "5") Integer pageSize) {

        CategoryPageQueryDTO categoryPageQueryDTO = new CategoryPageQueryDTO();
        categoryPageQueryDTO.setName(name);
        categoryPageQueryDTO.setType(type);
        categoryPageQueryDTO.setPageNum(pageNum);
        categoryPageQueryDTO.setPageSize(pageSize);

        log.info("分类分页查询：{}", categoryPageQueryDTO);

        PageBean pageBean = categoryService.pageQuery(categoryPageQueryDTO);

        return Result.success(pageBean);
    }

    @PostMapping("/status/{status}")
    @Operation(summary = "启用禁用分类")
    public Result updateStatus(@PathVariable Integer status, Integer id) {
        log.info("启用禁用分类：分类id{}，启禁用：{}", id, status);

        categoryService.updateStatus(status, id);

        return Result.success();
    }

    @PostMapping
    @Operation(summary = "新增分类")
    public Result add(@RequestBody CategoryAddDTO categoryAddDTO) {
        log.info("新增分类：{}", categoryAddDTO);

        categoryService.add(categoryAddDTO);

        return Result.success();
    }

    @DeleteMapping
    @Operation(summary = "根据id删除分类")
    public Result delete(@RequestParam Integer id) {
        log.info("根据id删除分类：{}", id);

        categoryService.delete(id);

        return Result.success();
    }

    @GetMapping("/list")
    @Operation(summary = "根据类型查询分类")
    public Result<List<Category>> selectByType(@RequestParam Integer type) {
        log.info("根据类型查询分类：{}", type);

        List<Category> list = categoryService.selectByType((type));

        return Result.success(list);
    }

}
