package com.liuweiliang.demo1.entity.model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
@Data
@TableName("erp_admins")
public class AdminModel implements Serializable {

    /**
    *
    */
    @NotNull(message="[]不能为空")
    @ApiModelProperty("")
    private Long id;
    /**
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
    /**
    * 创建人id
    */
    @NotNull(message="[创建人id]不能为空")
    @ApiModelProperty("创建人id")
    private Long creatorId;
    /**
    * 更新人id
    */
    @NotNull(message="[更新人id]不能为空")
    @ApiModelProperty("更新人id")
    private Long modifierId;
    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    private Date createdAt;
    /**
    * 更新时间
    */
    @ApiModelProperty("更新时间")
    private Date updatedAt;
    /**
    * 删除时间
    */
    @ApiModelProperty("删除时间")
    private Date deletedAt;

}
