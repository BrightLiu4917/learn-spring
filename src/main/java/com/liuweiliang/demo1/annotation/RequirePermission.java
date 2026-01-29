package com.liuweiliang.demo1.annotation;

import com.liuweiliang.demo1.enums.PermissionEnum;

import java.lang.annotation.*;

/**
 * 权限控制注解：标记方法需要的权限类型，仅拥有对应权限的用户可调用
 * 作用于：方法
 * 保留策略：运行时（AOP需要在运行时解析注解）
 */
@Target({ElementType.METHOD}) // 仅允许贴在方法上（接口/Service方法）
@Retention(RetentionPolicy.RUNTIME) // 运行时保留，AOP才能解析
@Documented // 生成API文档时显示该注解，便于团队协作
public @interface RequirePermission {
    /**
     * 注解属性：方法需要的权限类型
     * 默认值：普通管理员（ADMIN），删除账户接口手动指定为超级管理员（SUPER_ADMIN）
     */
    PermissionEnum value() default PermissionEnum.ADMIN;
}