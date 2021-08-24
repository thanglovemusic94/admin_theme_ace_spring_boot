package com.example.websitebanhang.configs;

import lombok.var;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import nz.net.ultraq.thymeleaf.decorators.strategies.GroupingStrategy;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.util.Collections;

@Configuration
public class ThymeleafConfigs implements WebMvcConfigurer {

    private static final String TEMPLATE_ENCODING = "UTF-8";
    private static final String TEMPLATE_PREFIX = "/templates/";
    private static final String TEMPLATE_PREFIX_EMAIL = "/email/";

    @Bean
    public Context getContext() {
        return new Context();
    }

    @Bean(name = "messageSource")
    public MessageSource getMessageResource() {
        ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
        messageResource.setBasename("classpath:messages");
        messageResource.setDefaultEncoding("UTF-8");
        return messageResource;
    }

    @Bean
    @Description("Thymeleaf view resolver")
    public ViewResolver viewResolver() {
        var viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }

    @Bean
    @Description("Thymeleaf template engine with Spring integration")
    public SpringTemplateEngine templateEngine() {
        var templateEngine = new SpringTemplateEngine();

        templateEngine.addTemplateResolver(htmlTemplateResolver());
        templateEngine.addTemplateResolver(textTemplateResolver());
        templateEngine.addTemplateResolver(stringTemplateResolver());

        templateEngine.addDialect(new LayoutDialect(new GroupingStrategy()));
        templateEngine.setTemplateEngineMessageSource(getMessageResource());
        return templateEngine;
    }


    public ITemplateResolver htmlTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setOrder(Integer.valueOf(1));
        templateResolver.setPrefix(TEMPLATE_PREFIX);
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);
        templateResolver.setCharacterEncoding(TEMPLATE_ENCODING);
        return templateResolver;
    }

    private ITemplateResolver textTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setOrder(Integer.valueOf(2));
        templateResolver.setResolvablePatterns(Collections.singleton("text/*"));
        templateResolver.setPrefix(TEMPLATE_PREFIX_EMAIL);
        templateResolver.setSuffix(".txt");
        templateResolver.setTemplateMode(TemplateMode.TEXT);
        templateResolver.setCharacterEncoding(TEMPLATE_ENCODING);
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    private ITemplateResolver stringTemplateResolver() {
        final StringTemplateResolver templateResolver = new StringTemplateResolver();
        templateResolver.setOrder(Integer.valueOf(3));
        templateResolver.setTemplateMode(TemplateMode.HTML5);
        templateResolver.setCacheable(false);
        return templateResolver;
    }

}
