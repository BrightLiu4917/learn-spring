package com.liuweiliang.demo1.common;

import lombok.Data;

/**
 * 统一JSON响应体
 * code：状态码（200=成功，500=失败，400=参数错误，404=资源不存在）
 * data：业务数据（支持单对象/数组/List，成功时返回，失败时为null）
 * message：提示信息（支持自定义，默认中文，可扩展多语言）
 */
@Data
public class Result<T> {
    // 状态码
    private Integer code;
    // 业务数据（泛型支持：单对象、List、数组等任意类型）
    private T data;
    // 提示信息
    private String message;

    // 私有构造：禁止外部直接实例化，统一通过静态方法调用
    private Result() {}

    // 全参构造：核心初始化逻辑
    private Result(Integer code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    // ========== 成功响应：灵活支持单对象/数组/无数据 ==========
    /**
     * 成功响应（单对象/数组 + 自定义提示）
     * @param data 单对象/List/数组均可
     * @param message 自定义提示语
     */
    public static <T> Result<T> success(T data, String message) {
        return new Result<>(200, data, message);
    }

    /**
     * 成功响应（仅单对象/数组，默认提示：操作成功）
     * @param data 单对象/List/数组均可
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(200, data, "操作成功");
    }

    /**
     * 成功响应（无数据，仅自定义提示）
     * @param message 自定义提示语
     */
    public static <T> Result<T> success(String message) {
        return new Result<>(200, null, message);
    }

    // ========== 失败响应：自定义状态码/提示 ==========
    /**
     * 失败响应（默认500状态码 + 自定义提示）
     * @param message 错误提示语
     */
    public static <T> Result<T> error(String message) {
        return new Result<>(500, null, message);
    }

    /**
     * 失败响应（自定义状态码 + 自定义提示）
     * @param code 错误状态码（如400=参数错误，404=资源不存在）
     * @param message 错误提示语
     */
    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, null, message);
    }

    // ========== 扩展：多语言提示（可选） ==========
    /**
     * 成功响应（单对象 + 英文默认提示）
     * @param data 单对象/List/数组均可
     */
    public static <T> Result<T> successEn(T data) {
        return new Result<>(200, data, "Operation successful");
    }
}