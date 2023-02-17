package com.example.demo.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Component
public class OfficeServiceInterceptorAppConfig  extends WebMvcConfigurerAdapter {
    @Autowired
    OfficeServiceInterceptor officeServiceInteceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(officeServiceInteceptor);
    }
}
