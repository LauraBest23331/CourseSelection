package com.zy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/Login").setViewName("views/login");
//        registry.addViewController("/UserList").setViewName("views/user_list");
//        registry.addViewController("/Main").setViewName("index");
        registry.addViewController("/").setViewName("views/login");

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new StudentHandlerInterceptor())
                .addPathPatterns("/Student/**")
                .excludePathPatterns("/Login", "/Login.action", "/",
                        "/css/**", "/js/**", "/img/**", "/error");
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/Root/**")
                .excludePathPatterns("/Login", "/Login.action", "/",
                        "/Student/**", "/Teacher/**", "/css/**", "/js/**", "/img/**", "/error");
        registry.addInterceptor(new TeacherHandlerInterceptor())
                .addPathPatterns("/Teacher/**");
    }
}
