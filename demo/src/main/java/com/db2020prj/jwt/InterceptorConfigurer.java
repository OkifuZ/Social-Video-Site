package com.db2020prj.jwt;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class InterceptorConfigurer implements WebMvcConfigurer {
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public FilterRegistrationBean servletRegistrationBean() {
        RequestFilter requestFilter = new RequestFilter();
        FilterRegistrationBean<RequestFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(requestFilter);
        bean.setName("requestFilter");
        bean.addUrlPatterns("/*");
        bean.setOrder(Ordered.LOWEST_PRECEDENCE);

        return bean;
    }
}
