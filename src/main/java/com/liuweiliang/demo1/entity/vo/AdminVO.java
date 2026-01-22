package com.liuweiliang.demo1.entity.vo;


import lombok.Data;
import java.util.Date;

@Data
public class AdminVO {


    /**
     *id
     */
    private Long id;
    /**
     * 邮箱
     */

    private String email;
    /**
     * 账号
     */

    private String account;
    /**
     * 真实姓名
     */

    private String realName;
    /**
     * 手机号
     */

    private String phone;
    /**
     * 密码
     */

//    private String password;
    /**
     * 状态:1正常,9禁用
     */

    private Integer enable;
    /**
     * 身份:1普通管理,9超级管理
     */

    private Integer ident;
    /**
     * 创建人id
     */

    private Long creatorId;
    /**
     * 更新人id
     */

    private Long modifierId;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新时间
     */
    private Date updatedAt;
}
