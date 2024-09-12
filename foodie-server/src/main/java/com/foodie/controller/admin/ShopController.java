package com.foodie.controller.admin;

import com.foodie.context.BaseContext;
import com.foodie.dto.ShopAddDTO;
import com.foodie.dto.ShopPageQueryDTO;
import com.foodie.entity.PageBean;
import com.foodie.result.Result;
import com.foodie.service.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/shop")
@Slf4j
@Tag(name = "店铺相关接口")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PostMapping("add")
    @Operation(summary = "新增店铺")
    public Result add(@RequestBody ShopAddDTO shopAddDTO) {
        log.info("新增店铺，操作者id：{}", BaseContext.getCurrentId());

        shopService.add(shopAddDTO);

        return Result.success();
    }

    @GetMapping("/page")
    @Operation(summary = "店铺分页查询")
    public Result<PageBean> pageQuery(@RequestParam(required = false) String name,
                                      @RequestParam(required = false) String restaurantNumber,
                                      @RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "5") Integer pageSize) {

        ShopPageQueryDTO shopPageQueryDTO = new ShopPageQueryDTO();
        shopPageQueryDTO.setName(name);
        shopPageQueryDTO.setRestaurantNumber(restaurantNumber);
        shopPageQueryDTO.setPageNum(pageNum);
        shopPageQueryDTO.setPageSize(pageSize);

        log.info("店铺分页查询：{}", shopPageQueryDTO);

        PageBean pageBean = shopService.pageQuery(shopPageQueryDTO);

        return Result.success(pageBean);
    }

    @PostMapping("/{status}")
    @Operation(summary = "设置店铺状态")
    public Result updateStatus(@PathVariable Integer status, Integer id){
        log.info("设置店铺状态，店铺id：{}，操作者id：{}", id, BaseContext.getCurrentId());

        shopService.updateStatus(status, id);

        return Result.success();
    }

    @DeleteMapping
    @Operation(summary = "删除店铺")
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("删除店铺，店铺ids：{}，操作者id：{}", ids, BaseContext.getCurrentId());

        shopService.delete(ids);

        return Result.success();
    }

}
