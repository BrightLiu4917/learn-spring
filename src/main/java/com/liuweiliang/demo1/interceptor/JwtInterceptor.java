package com.liuweiliang.demo1.interceptor;

import com.liuweiliang.demo1.common.Result;
import com.liuweiliang.demo1.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * JWT拦截器：校验请求头中的Token合法性
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 请求处理前执行（核心：校验Token）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 提取请求头中的Token（前端通常将Token放在Authorization头中，格式：Bearer {Token}）
        String authorization = request.getHeader("Authorization");
        String token = null;
        if (authorization != null) {
            if (authorization.startsWith("Bearer ")){
                token = authorization.substring(7);// 截取Token（去掉"Bearer "前缀）
            }else{
                token = authorization.trim();
            }
        }

        System.out.println("Token：" + token);

        // 2. 校验Token合法性
        if (token == null || !jwtUtil.verifyToken(token)) {
            // 3. Token非法/为空，返回统一的未登录提示（JSON格式）
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            // 封装Result对象，转为JSON字符串返回
            Result<Void> result = Result.fail(401, "未登录或Token已失效，请先登录");
            writer.write(new ObjectMapper().writeValueAsString(result));
            writer.flush();
            writer.close();
            return false; // 拦截请求，不放行
        }

        // 4. Token合法，解析Token获取用户信息（可选，存入RequestContextHolder供后续业务使用）
        // Map<String, Object> userInfo = jwtUtil.parseToken(token);
        // RequestContextHolder.getRequestAttributes().setAttribute("userInfo", userInfo, 0);

        // 5. 放行请求，执行接口逻辑
        return true;
    }
}