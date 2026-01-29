package com.liuweiliang.demo1.aspect;

import com.liuweiliang.demo1.annotation.RequirePermission;
import com.liuweiliang.demo1.common.Result;
import com.liuweiliang.demo1.enums.PermissionEnum;
import com.liuweiliang.demo1.util.JwtUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Aspect
@Component
public class PermissionAspect {

    @Resource
    private JwtUtil jwtUtil;

    // 拦截所有贴了@RequirePermission的方法
    @Around("@annotation(requirePermission)")
    public Object checkPermission(ProceedingJoinPoint joinPoint, RequirePermission requirePermission) throws Throwable {
        // 1. 从注解里获取枚举值（用户要求的权限）

        PermissionEnum requiredPerm = requirePermission.value();
        System.out.println("需要的权限：" + requiredPerm.getPermDesc() + "（码：" + requiredPerm.getPermCode() + "）");

        // 2. 获取当前登录用户的权限（模拟：从token解析，实际业务从数据库/缓存取）
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userPermCode = request.getHeader("permCode"); // 比如传"1"（对应ADMIN）

        String token = request.getHeader("Authorization");
        //System.out.println("用户权限码（请求头传入）：" + jwtUtil.parseToken(token.substring(7).trim()));

        Map<String, Object> authInfo = jwtUtil.parseToken(token.substring(7).trim());

        PermissionEnum userPerm = getPermByCode((int) authInfo.get("permCode"));

        // 3. 权限校验：判断当前用户是否有要求的权限
        if (!requiredPerm.equals(userPerm)) {
            return Result.fail(403, "无权限：需要" + requiredPerm.getPermDesc() + "权限");
        }

        // 4. 权限通过，执行原方法
        return joinPoint.proceed();
    }

    // 辅助方法：根据权限码获取枚举（模拟）
    private PermissionEnum getPermByCode(int code) {
        for (PermissionEnum perm : PermissionEnum.values()) {
            if (perm.getPermCode() == code) {
                return perm;
            }
        }
        return PermissionEnum.ADMIN;
    }
}