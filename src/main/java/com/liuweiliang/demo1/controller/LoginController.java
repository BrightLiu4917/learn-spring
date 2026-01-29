package com.liuweiliang.demo1.controller;

import com.liuweiliang.demo1.common.Result;
import com.liuweiliang.demo1.entity.model.AdminModel;
import com.liuweiliang.demo1.entity.req.LoginRequest;
import com.liuweiliang.demo1.service.impl.Admin.AdminServiceImpl;
import com.liuweiliang.demo1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * 登录接口：生成JWT Token返回给前端
 */
@RestController
@RequestMapping("/api/v1")
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;

    @Resource
    AdminServiceImpl adminService;

    /**
     *
     * @param request
     * @return
     */
    @PostMapping("/login")
    public Result<String> login(
            @RequestBody LoginRequest request
    ) {
        AdminModel adminModel = adminService.getAdminByUsername(request.getAccount(),request.getPassword());
        int accountId = adminModel.getId().intValue();
        int permCode = accountId == 1 ? 1 : 0;
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", adminModel.getId()); // 用户ID
        claims.put("account", adminModel.getAccount()); // 用户名
        claims.put("permCode", permCode); // 权限码（0=超级管理员，1=普通管理员）
        String token = jwtUtil.generateToken(claims);
        return Result.success(token);
    }
}