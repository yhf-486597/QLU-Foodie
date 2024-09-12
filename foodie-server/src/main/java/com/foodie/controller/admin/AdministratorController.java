package com.foodie.controller.admin;

import com.foodie.constant.JwtClaimsConstant;
import com.foodie.context.BaseContext;
import com.foodie.dto.*;
import com.foodie.entity.Administrator;
import com.foodie.entity.PageBean;
import com.foodie.properties.JwtProperties;
import com.foodie.result.Result;
import com.foodie.service.AdministratorService;
import com.foodie.untils.JwtUtil;
import com.foodie.vo.AdministratorLoginVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/administrator")
@Slf4j
@Tag(name = "管理员相关接口")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private JwtProperties jwtProperties;

    //管理员登录
    @PostMapping("/login")
    @Operation(summary = "管理员登录")
    public Result<AdministratorLoginVO> login(@RequestBody AdministratorLoginDTO administratorLoginDTO) {
        log.info("管理员登录：{}", administratorLoginDTO);

        Administrator administrator = administratorService.login(administratorLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.ADMINISTRATOR_ID, administrator.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        AdministratorLoginVO administratorLoginVO = AdministratorLoginVO.builder()
                .id(administrator.getId())
                .username(administrator.getUsername())
                .name(administrator.getName())
                .token(token)
                .build();

        return Result.success(administratorLoginVO);
    }

    //管理员退出
    @PostMapping("/logout")
    @Operation(summary = "管理员退出")
    public Result logout() {
        log.info("管理员退出，管理员id：{}", BaseContext.getCurrentId());

        return Result.success();
    }

    //根据id查询管理员
    @GetMapping("/{id}")
    @Operation(summary = "根据id查询管理员")
    public Result<Administrator> selectById(@PathVariable Integer id){
        log.info("根据id查询管理员，管理员id：{}", id);

        Administrator administrator = administratorService.selectById(id);

        return Result.success(administrator);
    }

    //新增管理员
    @PostMapping("/add")
    @Operation(summary = "新增管理员")
    public Result add(@RequestBody AdministratorAddDTO administratorAddDTO){
        log.info("新增管理员，操作者id：{}", BaseContext.getCurrentId());

        administratorService.add(administratorAddDTO);

        return Result.success();
    }

    //管理员修改密码
    @PutMapping("/editPassword")
    @Operation(summary = "管理员修改密码")
    public Result editPassword(@RequestBody AdministratorEditPasswordDTO administratorEditPasswordDTO){
        log.info("管理员修改密码，管理员id：{}", BaseContext.getCurrentId());

        administratorService.editPassword(administratorEditPasswordDTO);

        return Result.success();
    }

    //启用、禁用管理员
    @PostMapping("/status/{status}")
    @Operation(summary = "启用禁用管理员")
    public Result updateStatus(@PathVariable Integer status, Integer id){
        log.info("启用、禁用管理员，管理员id：{}", id);

        administratorService.updateStatus(status, id);

        return Result.success();
    }

    //编辑管理员信息
    @PutMapping
    @Operation(summary = "编辑管理员信息")
    public Result update(@RequestBody AdministratorEditDTO administratorEditDTO){
        log.info("编辑管理员信息，管理员id：{}", administratorEditDTO.getId());

        administratorService.update(administratorEditDTO);

        return Result.success();
    }

    //管理员分页查询
    @GetMapping("/page")
    @Operation(summary = "管理员分页查询")
    public Result<PageBean> pageQuery(@RequestParam(required = false) String name,
                                      @RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "5") Integer pageSize){

        AdministratorPageQueryDTO administratorPageQueryDTO = new AdministratorPageQueryDTO();
        administratorPageQueryDTO.setName(name);
        administratorPageQueryDTO.setPageNum(pageNum);
        administratorPageQueryDTO.setPageSize(pageSize);

        log.info("管理员分页查询：{}", administratorPageQueryDTO);

        PageBean pageBean = administratorService.pageQuery(administratorPageQueryDTO);

        return Result.success(pageBean);
    }

    //删除管理员
    @DeleteMapping
    @Operation(summary = "删除管理员")
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除管理员，管理员id：{}，操作者：{}", ids, BaseContext.getCurrentId());

        administratorService.delete(ids);

        return Result.success();
    }
}
