package com.liuweiliang.demo1.common;

import lombok.Data;

/**
 * 通用返回结果封装类，用于向前端返回统一格式的数据
 * @param <T> data字段的类型，支持单对象、集合等
 */
@Data // 省去getter/setter/toString等方法的编写，需要引入lombok依赖
public class Result<T> {

    /**
     * 业务状态码（如200成功、500失败、404未找到等）
     */
    private Integer code;

    /**
     * 状态描述（如"success"、"error"、"数据不存在"等）
     */
    private String status;

    /**
     * 具体返回数据（单条数据时为单个对象，多条时为集合）
     */
    private T data;

    // 私有构造方法，避免外部直接new，统一通过静态方法构建
    private Result() {}

    // 私有构造方法，接收所有字段
    private Result(Integer code, String status, T data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }

    // ========== 静态构建方法（简化使用） ==========

    /**
     * 成功返回（带单条数据/自定义数据）
     * @param data 要返回的单条数据（单个对象）
     * @param <T>  数据类型
     * @return Result<T>
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data);
    }

    /**
     * 成功返回（无数据，仅返回状态）
     * @param <T> 泛型占位
     * @return Result<T>
     */
    public static <T> Result<T> success() {
        return new Result<>(200, "success", null);
    }

    /**
     * 失败返回（自定义状态码、描述、无数据）
     * @param code   错误码
     * @param status 错误描述
     * @param <T>    泛型占位
     * @return Result<T>
     */
    public static <T> Result<T> fail(Integer code, String status) {
        return new Result<>(code, status, null);
    }

    /**
     * 失败返回（自定义状态码、描述、带错误数据）
     * @param code   错误码
     * @param status 错误描述
     * @param data   错误相关数据
     * @param <T>    数据类型
     * @return Result<T>
     */
    public static <T> Result<T> fail(Integer code, String status, T data) {
        return new Result<>(code, status, data);
    }
}