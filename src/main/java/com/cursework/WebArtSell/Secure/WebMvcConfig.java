package com.cursework.WebArtSell.Secure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RoleCheckInterceptor()).addPathPatterns("/table-users", "/table-transactions", "/table-products", "/product-edit");
        registry.addInterceptor(new UserCheckInterceptor()).addPathPatterns("/billing/**", "/table-products/add", "/product-details/**", "/main-user", "/table-products/*");
    }

}
