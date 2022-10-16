package com.example.demo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:validation");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:E:\\Dok\\Java\\Hospital\\src\\main\\resources\\static\\images\\bagel.png")
                .addResourceLocations("file:E:\\Dok\\Java\\Hospital\\src\\main\\resources\\static\\images\\footer1.jpg")
                .addResourceLocations("file:E:\\Dok\\Java\\Hospital\\src\\main\\resources\\static\\images\\footer2.jpg")
                .addResourceLocations("file:E:\\Dok\\Java\\Hospital\\src\\main\\resources\\static\\images\\footer3.jpg")
                .addResourceLocations("file:E:\\Dok\\Java\\Hospital\\src\\main\\resources\\static\\images\\footer4.jpg")
                .addResourceLocations("file:E:\\Dok\\Java\\Hospital\\src\\main\\resources\\static\\images\\footer5.jpg")
                .addResourceLocations("file:E:\\Dok\\Java\\Hospital\\src\\main\\resources\\static\\images\\footer6.jpg")
                .addResourceLocations("file:E:\\Dok\\Java\\Hospital\\src\\main\\resources\\static\\images\\footer7.jpg")
                .addResourceLocations("file:E:\\Dok\\Java\\Hospital\\src\\main\\resources\\static\\images\\about.jpg")
                .addResourceLocations("file:E:\\Dok\\Java\\Hospital\\src\\main\\resources\\static\\images\\welcome1.jpg")
                .addResourceLocations("file:E:\\Dok\\Java\\Hospital\\src\\main\\resources\\static\\images\\welcome2.jpg");
    }
}
