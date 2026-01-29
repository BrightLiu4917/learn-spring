package com.liuweiliang.demo1.enums;

import lombok.Getter;

/**
 * 权限枚举：限定系统所有权限类型，删除账户仅支持SUPER_ADMIN
 */
@Getter // Lombok生成getter，方便AOP/业务层获取属性
public enum PermissionEnum {
    /**
     * 超级管理员：拥有所有权限（包含删除账户）
     * 普通管理员：仅拥有查询/编辑等基础权限，无删除权限
     */
    SUPER_ADMIN(1, "超级管理员", "拥有账户删除、配置等所有系统权限"),
    ADMIN(0, "普通管理员", "仅拥有账户查询、编辑等基础权限，无删除权限");

    // 权限码：存入JWT，轻量易传输
    private final Integer permCode;
    // 权限名称：前端展示/日志打印
    private final String permName;
    // 权限描述：详细说明，便于维护
    private final String permDesc;

    // 枚举构造器必须为private，外部无法new
    PermissionEnum(Integer permCode, String permName, String permDesc) {
        this.permCode = permCode;
        this.permName = permName;
        this.permDesc = permDesc;
    }

    /**
     * 辅助方法：根据权限码获取枚举（核心：JWT解析出权限码后转成枚举，方便对比）
     * @param permCode 权限码（0=超级管理员，1=普通管理员）
     * @return 对应的权限枚举，默认返回普通管理员
     */
    public static PermissionEnum getByCode(Integer permCode) {
        for (PermissionEnum perm : PermissionEnum.values()) {
            if (perm.getPermCode().equals(permCode)) {
                return perm;
            }
        }
        // 非法权限码默认返回普通管理员（降低系统风险）
        return ADMIN;
    }
}