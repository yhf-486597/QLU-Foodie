package com.foodie.controller.admin;

import com.foodie.context.BaseContext;
import com.foodie.dto.RiderApplyDTO;
import com.foodie.dto.RiderPageQueryDTO;
import com.foodie.entity.PageBean;
import com.foodie.entity.Rider;
import com.foodie.result.Result;
import com.foodie.service.RiderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/rider")
@Slf4j
@Tag(name = "骑手相关接口")
public class RiderController {

    @Autowired
    private RiderService riderService;

    //骑手分页查询
    @GetMapping("/page")
    @Operation(summary = "骑手分页查询")
    public Result<PageBean> pageQuery(@RequestParam(required = false) String name,
                                      @RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "5") Integer pageSize) {

        RiderPageQueryDTO riderPageQueryDTO = new RiderPageQueryDTO();
        riderPageQueryDTO.setName(name);
        riderPageQueryDTO.setPageNum(pageNum);
        riderPageQueryDTO.setPageSize(pageSize);

        log.info("骑手分页查询：{}", riderPageQueryDTO);

        PageBean pageBean = riderService.pageQuery(riderPageQueryDTO);

        return Result.success(pageBean);
    }

    //删除骑手
    @DeleteMapping
    @Operation(summary = "删除骑手")
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除骑手，骑手id：{}，操作者：{}", ids, BaseContext.getCurrentId());

        riderService.delete(ids);

        return Result.success();
    }

    //根据id查询骑手
    @GetMapping("/{id}")
    @Operation(summary = "根据id查询骑手")
    public Result<Rider> selectById(@PathVariable Integer id){
        log.info("根据id查询骑手，骑手id：{}", id);

        Rider rider = riderService.selectById(id);

        return Result.success(rider);
    }

    //审核骑手申请
    @PutMapping("/review")
    @Operation(summary = "审核骑手申请")
    public Result review(@RequestBody RiderApplyDTO riderApplyDTO){
        log.info("审核骑手申请，管理员id：{}", BaseContext.getCurrentId());

        riderService.review(riderApplyDTO);

        return Result.success();
    }

    //启用禁用骑手
    @PostMapping("/status/{status}")
    @Operation(summary = "启用禁用骑手")
    public Result updateStatus(@PathVariable Integer status, Integer id){
        log.info("启用禁用骑手，骑手id：{}，操作者id：{}", id, BaseContext.getCurrentId());

        riderService.updateStatus(status, id);

        return Result.success();
    }
}
