package cn.czfshine.tiku.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 配置静态资源
 *
 * @author:czfshine
 * @date:2019/3/22 16:53
 */
@Slf4j
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "cn.czfshine.tiku")
public class StaticFileConfig implements WebMvcConfigurer {

    /**
     * 配置静态资源路径
     *
     * @param registry registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/web/**")
//                .addResourceLocations("classpath:/static/index.html");

        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/js/");

        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");

        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("classpath:/favicon.ico");

        registry.addResourceHandler("/manifest.json")
                .addResourceLocations("classpath:/manifest.json");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

    }

    /**
     * CROS跨域的处理
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
                .allowCredentials(true).maxAge(3600);
    }

}