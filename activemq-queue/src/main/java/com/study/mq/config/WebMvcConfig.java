package com.study.mq.config;

import com.study.mq.Interceptor.OneInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * <br>
 * 标题: 自定义配置类<br>
 * 描述: 注册自定义的拦截器<br>
 *
 * @author shiboxue
 * @date 2019/09/30
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /**
     * @name SpringMVC 跳转jsp 配置
     * @return viewResolver 视图解析器
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");//使用tomcat7:run插件后要放的位置
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    /**
     * @name 添加拦截器，并设置拦截访问路径
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 拦截器按照顺序执行
         */
       /* registry.addInterceptor(new TwoInterceptor()).addPathPatterns("/two/**").addPathPatterns("/one/**");*/
        registry.addInterceptor(new OneInterceptor()).addPathPatterns("/user/**");
        super.addInterceptors(registry);
    }

    /**
     * @name Swagger访问地址配置
     * @param registry
     */
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
