package com.example.websitebanhang.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
@PropertySource(value={"classpath:application.properties"})
public class I18NConfig implements WebMvcConfigurer {

//    @Value("${spring.mvc.locale}")
//    private Locale locale;

    @Bean(name = "localeResolver")
    public LocaleResolver getLocaleResolver() {

        SessionLocaleResolver slr = new SessionLocaleResolver();
        //slr.setDefaultLocale(locale);
        return slr;
    }


    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor()).addPathPatterns("/**");
    }

}
