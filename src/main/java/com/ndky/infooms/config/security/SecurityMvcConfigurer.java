package com.ndky.infooms.config.security;

import com.ndky.infooms.config.interceptor.AccessAuthHandlerInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author chenqingsheng
 * @date 2021/1/23 21:41
 */
@AllArgsConstructor
@Configuration
public class SecurityMvcConfigurer implements WebMvcConfigurer {

    /**
     * url访问权限验证拦截器
     * accessAuthHandlerInterceptor
     */
    private AccessAuthHandlerInterceptor accessAuthHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册过滤器
        registry.addInterceptor(accessAuthHandlerInterceptor).excludePathPatterns("/static/**")
                .addPathPatterns("/**");
    }


    /**
     * 页面跳转
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login.html");
        registry.addViewController("/403").setViewName("403.html");

    }

}
