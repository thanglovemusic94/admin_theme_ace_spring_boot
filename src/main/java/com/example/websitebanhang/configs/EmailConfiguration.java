package com.example.websitebanhang.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = { "com.example.websitebanhang.configs" })
//@PropertySource(value={"classpath:email/emailconfig.properties"})
public class EmailConfiguration {

    private static final String Host = "smtp.gmail.com";
    private static final Integer Port = 587;
    private static final String Username = "tutorreact@gmail.com";
    private static final String Password = "tvzjdbqpbhaclhna";
    private static final String Auth = "true";
    private static final String StartTls = "true";
    private static final String Debug = "true";
    private static final String Protocol = "smtp";

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(Host);
        mailSender.setPort(Port);
        mailSender.setUsername(Username);
        mailSender.setPassword(Password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", Protocol);
        props.put("mail.smtp.auth", Auth);
        props.put("mail.smtp.starttls.enable", StartTls);
        props.put("mail.debug", Debug);

        return mailSender;
    }

    @Bean
    public SimpleMailMessage templateSimpleMessage() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        return mailMessage;
    }

}
