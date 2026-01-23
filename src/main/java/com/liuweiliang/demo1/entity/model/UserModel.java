package com.liuweiliang.demo1.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@TableName("users")
@Data
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Long id;
}