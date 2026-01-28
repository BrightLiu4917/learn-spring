package com.liuweiliang.demo1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class MyGlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {

        // 1. 从异常对象里拿到 BindingResult
        BindingResult bindingResult = ex.getBindingResult();

        // 2. 创建一个 Map 用来存放我们要返回给前端的数据
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("code", 400); // HTTP 状态码
        responseMap.put("message", "请求参数校验失败"); // 总体错误信息

        // 3. 创建一个列表，用来存放每一个具体的错误字段信息
        List<Map<String, String>> errors = new ArrayList<>();

        // 4. 遍历所有校验失败的字段
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            // 5. 每一个 FieldError 对象都代表一个字段的错误
            Map<String, String> errorDetail = new HashMap<>();
            errorDetail.put("field", fieldError.getField()); // 错误的字段名，比如 "name"
            errorDetail.put("message", fieldError.getDefaultMessage()); // 错误信息，比如 "用户名不能为空"
            errorDetail.put("rejectedValue", String.valueOf(fieldError.getRejectedValue())); // 用户输入的错误值

            // 6. 把这个错误详情加到列表里
            errors.add(errorDetail);
        }

        // 7. 把错误列表放到总的响应里
        responseMap.put("errors", errors);

        // 8. 返回 ResponseEntity，可以设置 HTTP 状态码和响应体
        return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
    }
}