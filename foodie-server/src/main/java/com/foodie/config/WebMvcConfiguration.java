package com.foodie.config;

import com.foodie.interceptor.JwtAdminInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//配置类，注册Web层相关组件
@Slf4j
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private JwtAdminInterceptor jwtAdminInterceptor;

    //注册自定义拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("开始自定义注册拦截器...");

        registry.addInterceptor(jwtAdminInterceptor)
                //需要拦截的请求
                .addPathPatterns("/admin/**")
                //需要排除的请求
                .excludePathPatterns("/admin/administrator/login");
    }

    //设置静态资源映射
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/v3/**")
                .addResourceLocations("classpath:/META-INF/resources/v3/");
    }

}
