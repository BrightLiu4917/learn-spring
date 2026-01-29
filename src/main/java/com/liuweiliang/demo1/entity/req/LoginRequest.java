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
public class LoginRequest {




    /**
     * 账号
     */
    @NotBlank(message="[账号]不能为空")
    @Size(max= 32,message="编码长度不能超过32")
    @ApiModelProperty("账号")
    @Length(max= 32,message="编码长度不能超过32")
    private String account;




    /**
     * 密码
     */
    @NotBlank(message="[密码]不能为空")
    @Size(max= 64,message="编码长度不能超过64")
    @ApiModelProperty("密码")
    @Length(max= 64,message="编码长度不能超过64")
    private String password;

}
