package com.liuweiliang.demo1.entity.model;

import com.baomidou.mybatisplus.annotation.TableName;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@TableName("users")
public class UserModel {
    private String name;
    private Long id;
}