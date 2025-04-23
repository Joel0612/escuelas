package com.js.escuelas.config;

import java.util.Locale;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Bean
    public SessionLocaleResolver localeResolver(){
         SessionLocaleResolver slr = new SessionLocaleResolver();
         slr.setDefaultLocale(new Locale("es"));
         return slr;
    }

     @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

   @Override
    public void addInterceptors(InterceptorRegistry registro){
        registro.addInterceptor(localeChangeInterceptor());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/errores/403").setViewName("errores/403");
    }

}
