package edu.develop.leave.config;

import edu.develop.leave.config.intercepors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @version 1.0
 * @anthor on 2019/5/7
 * @since jdk8
 */

//@Component
public class WebConfigurer extends WebMvcConfigurerAdapter {

    @Autowired
    private LoginInterceptor loginInterceptor;

    // 这个方法是用来配置静态资源的，比如html，js，css，等等
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

    /**
     * 注册拦截器
     * <code>addPathPatterns()<code/>配置拦截请求
     * <code>excludePathPatterns()</code>配置白名单，多个路径用<code>,<code/>
     * 将字符串隔开
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/html/login.html",
                        "/login", "/","/44551/login","/resource/**","/css/**","/js/**","/images/**","/7788"
                ,"/html/manager/login.html","/manager/login");
        super.addInterceptors(registry);
    }

    /**
     * @param registry
     */
    public void addViewControllers(ViewControllerRegistry registry){
         registry.addViewController("/").setViewName("login");
         registry.addViewController("/7788").setViewName("manager/login");
         super.addViewControllers(registry);
    }
}
