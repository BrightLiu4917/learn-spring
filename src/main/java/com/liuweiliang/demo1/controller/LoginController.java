package com.liuweiliang.demo1.controller;

import com.liuweiliang.demo1.annotation.RequirePermission;
import com.liuweiliang.demo1.common.Result;
import com.liuweiliang.demo1.entity.model.AdminModel;
import com.liuweiliang.demo1.entity.req.LoginRequest;
import com.liuweiliang.demo1.enums.PermissionEnum;
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
    public Result<Map<String, Object>> login(
            @RequestBody LoginRequest request
    ) {
        try {
            AdminModel adminModel = adminService.getAdminByUsername(request.getAccount(), request.getPassword());
            int accountId = adminModel.getId().intValue();
            int permCode = accountId == 1 ? 1 : 0;
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", adminModel.getId()); // 用户ID
            claims.put("account", adminModel.getAccount()); // 用户名
            claims.put("permCode", permCode); // 权限码（0=超级管理员，1=普通管理员）
            String token = jwtUtil.generateToken(claims);
            Map<String, Object> result = new HashMap<>();
            result.put("access_token", token);
            result.put("access_token_type", "Bearer");
            result.put("expires_in",jwtUtil.getExpireTime()/1000);
            return Result.success(result);
        } catch (RuntimeException e) {
            return Result.fail(500, "用户名或密码错误");
        }
    }


    @GetMapping("auth/me")
    public  Result<Map<String, Object>> me(@RequestHeader("Authorization") String authorization)
    {
        String token = authorization.substring(7);
        Map<String, Object> userInfo = jwtUtil.parseToken(token);
        return Result.success(userInfo);
    }


    @GetMapping("auth/delete")
    @RequirePermission(PermissionEnum.SUPER_ADMIN) // 仅超级管理员可调用
    public Result<Boolean> delete(@RequestParam String id)
    {
        adminService.removeById(id);
        return Result.success(true);
    }

}