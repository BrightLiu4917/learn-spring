package com.liuweiliang.demo1.entity.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor// 强制生成无参构造函数
public class AdminCreateRequest {


    /*
     * 邮箱
     */
    @NotBlank(message="[邮箱]不能为空")
    @Size(max= 32,message="编码长度不能超过32")
    @ApiModelProperty("邮箱")
    @Length(max= 32,message="编码长度不能超过32")
    private String email;

    /**
     * 账号
     */
    @NotBlank(message="[账号]不能为空")
    @Size(max= 32,message="编码长度不能超过32")
    @ApiModelProperty("账号")
    @Length(max= 32,message="编码长度不能超过32")
    private String account;

    /**
     * 真实姓名
     */
    @NotBlank(message="[真实姓名]不能为空")
    @Size(max= 32,message="编码长度不能超过32")
    @ApiModelProperty("真实姓名")
    @Length(max= 32,message="编码长度不能超过32")
    @JsonProperty("real_name") // 关键：强制 JSON 中的字段名为 "real_name"
    private String realName;

    /**
     * 手机号
     */
    @NotBlank(message="[手机号]不能为空")
    @Size(max= 32,message="编码长度不能超过32")
    @ApiModelProperty("手机号")
    @Length(max= 32,message="编码长度不能超过32")
    private String phone;
    /**
     * 密码
     */
    @NotBlank(message="[密码]不能为空")
    @Size(max= 64,message="编码长度不能超过64")
    @ApiModelProperty("密码")
    @Length(max= 64,message="编码长度不能超过64")
    private String password;
    /**
     * 状态:1正常,9禁用
     */
    @NotNull(message="[状态:1正常,9禁用]不能为空")
    @ApiModelProperty("状态:1正常,9禁用")
    private Integer enable;
    /**
     * 身份:1普通管理,9超级管理
     */
    @NotNull(message="[身份:1普通管理,9超级管理]不能为空")
    @ApiModelProperty("身份:1普通管理,9超级管理")
    private Integer ident;

}
