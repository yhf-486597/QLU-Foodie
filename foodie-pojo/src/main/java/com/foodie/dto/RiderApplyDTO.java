package com.foodie.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "审核骑手申请时传递的数据模型")
public class RiderApplyDTO implements Serializable {

    @Schema(description = "id")
    private Integer id;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "性别")
    private String sex;

    @Schema(description = "身份证号")
    private String idNumber;

    @Schema(description = "审核状态，0未审核 1审核通过 2审核未通过")
    private Integer status;

}
