package com.liuweiliang.demo1.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 必须加！MyBatis-Plus分页完全依赖这个插件
 */
@Configuration // 标记为配置类，Spring启动时加载
public class MyBatisPlusConfig {

    @Bean // 把分页插件交给Spring管理
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 1. 添加分页插件，指定数据库类型（必须和你的数据库一致，比如MySQL）
        PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
        // 2. 开启溢出分页（比如页码超过总页数，自动返回最后一页，避免报错）
        paginationInterceptor.setOverflow(true);
        // 3. 设置最大单页限制（可选，防止分页参数过大）
        paginationInterceptor.setMaxLimit(1000L);
        // 4. 把分页插件添加到拦截器链
        interceptor.addInnerInterceptor(paginationInterceptor);
        return interceptor;
    }
}