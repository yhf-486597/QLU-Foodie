package com.foodie.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "编辑管理员时传递的数据模型")
public class AdministratorEditDTO implements Serializable {

    @Schema(description = "id")
    private Integer id;

    @Schema(description = "身份证")
    private String idNumber;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "性别")
    private String sex;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "状态：1启用 0禁用")
    private Integer status;

    @Schema(description = "权限：1TOP 0普通")
    private Integer isTop;

}
