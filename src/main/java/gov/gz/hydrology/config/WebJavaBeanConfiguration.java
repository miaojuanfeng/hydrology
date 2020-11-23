package gov.gz.hydrology.config;

import gov.gz.hydrology.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web配置类
 */
@Configuration
public class WebJavaBeanConfiguration {
    /**
     * 日志拦截器
     */
    @Autowired
    private LoginInterceptor loginInterceptor;
    /**
     * 实例化WebMvcConfigurer接口
     *
     * @return
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            /**
             * 添加拦截器
             * @param registry
             */
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(loginInterceptor)
//                        .excludePathPatterns("/cms/user/test")
//                        .excludePathPatterns("/cms/user/login")
//                        .excludePathPatterns("/cms/user/register")
//                        .excludePathPatterns("/cms/user/logout")
//                        .addPathPatterns("/cms/**");
                        .excludePathPatterns("/view/**")
                        .excludePathPatterns("/cms/**");
            }
        };
    }
}
