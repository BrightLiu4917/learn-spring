package com.liuweiliang.demo1.exception;

import com.liuweiliang.demo1.common.Result; // 导入你项目的统一Result类
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.StringJoiner;

/**
 * 全局异常处理器：
 * 1. 处理@Valid + @RequestBody的参数校验异常（MethodArgumentNotValidException）
 * 2. 处理@RequestParam(required=true)的必填异常（MissingServletRequestParameterException）
 * 3. 兜底处理所有其他异常
 * 4. 统一返回项目的Result格式，前端只需适配一种格式
 */
@RestControllerAdvice
public class MyGlobalExceptionHandler {

    // 1. 处理@Valid + @RequestBody的参数校验异常（比如POST请求的JSON参数校验）
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Object> handleValidException(MethodArgumentNotValidException ex) {
        // 拼接所有字段的错误提示（多个字段报错时，用逗号分隔）
        StringJoiner errorMsg = new StringJoiner("，");
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            // 错误提示：字段名 + 提示信息（比如 "account：账号不能为空"）
            errorMsg.add(fieldError.getField() + "：" + fieldError.getDefaultMessage());
        }
        // 返回统一Result格式，code=400（参数错误）
        return Result.fail(400, "参数校验失败：" + errorMsg.toString());
    }

    // 2. 处理@RequestParam(required=true)的必填异常（GET请求参数缺失）
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<Object> handleMissingParamException(MissingServletRequestParameterException ex) {
        // 友好提示：参数名 + "不能为空"
        String errorMsg = ex.getParameterName() + "参数不能为空";
        // 返回统一Result格式，code=400
        return Result.fail(400, errorMsg);
    }

    // 3. 兜底处理所有未定义的异常（比如空指针、SQL异常等）
    @ExceptionHandler(Exception.class)
    public Result<Object> handleAllException(Exception ex, HttpServletRequest request) {
        // 生产环境：隐藏具体异常信息，只返回通用提示；开发环境可打印异常栈
        String errorMsg = "服务器内部错误，请稍后重试";
        // 开发环境调试用（可选）：打印异常栈
        ex.printStackTrace();
        // 返回统一Result格式，code=500
        return Result.fail(500, errorMsg);
    }
}