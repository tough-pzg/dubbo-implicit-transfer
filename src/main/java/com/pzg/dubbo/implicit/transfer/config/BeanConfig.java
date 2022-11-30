package com.pzg.dubbo.implicit.transfer.config;

import com.pzg.dubbo.implicit.transfer.interceptor.ControllerHandlerInterceptor;
import com.pzg.dubbo.implicit.transfer.interceptor.DubboConsumerContextClusterInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author pzg
 */
@Configuration
@Import({ControllerHandlerInterceptor.class, DubboConsumerContextClusterInterceptor.class})
public class BeanConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private ControllerHandlerInterceptor controllerHandlerInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(controllerHandlerInterceptor).order(99);
    }

}
