package com.example.websitebanhang.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

@Service("IEmailService")
public class EmailService implements IEmailService {

    private static final String NOREPLY_ADDRESS = "noreply@baeldung.com";//something@something.com

    private final JavaMailSender emailSender;

    private final SimpleMailMessage mailMessage;

    private final SpringTemplateEngine thymeleafTemplateEngine;



    @Value("classpath:/email/mail-logo.png")
    private Resource resourceFile;

    public EmailService(JavaMailSender emailSender, SimpleMailMessage mailMessage, SpringTemplateEngine thymeleafTemplateEngine) {
        this.emailSender = emailSender;
        this.mailMessage = mailMessage;
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
    }

    public void sendSimpleMessage(String to, String subject, String text) throws MessagingException {
//        try {
            SimpleMailMessage message = new SimpleMailMessage();
//            //message.setFrom(NOREPLY_ADDRESS);
//            message.setTo(to);
//            message.setSubject(subject);
//            message.setText(text);
//
//            emailSender.send(message);
//        } catch (MailException exception) {
//            exception.printStackTrace();
//        }

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(text, true);
        helper.setTo(to);
        helper.setSubject(subject);
        emailSender.send(mimeMessage);
    }

    @Override
    public void sendSimpleMessageUsingTemplate(String to,
                                               String subject,
                                               String ...templateModel) throws MessagingException {
        mailMessage.setText("This is the test email template for your email:\n%s\n");
        String text = String.format(mailMessage.getText(), templateModel);
        sendSimpleMessage(to, subject, text);
    }

    @Override
    public void sendMessageWithAttachment(String to,
                                          String subject,
                                          String text,
                                          String pathToAttachment) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            // pass 'true' to the constructor to create a multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(NOREPLY_ADDRESS);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment("Invoice", file);

            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void sendMessageUsingThymeleafTemplate(
            String to, String subject, Map<String, Object> templateModel)
            throws MessagingException {

        Context thymeleafContext = new Context();
        thymeleafContext.setVariables(templateModel);

        String htmlBody = thymeleafTemplateEngine.process("email/email-template-thymeleaf", thymeleafContext);

        sendHtmlMessage(to, subject, htmlBody);
    }


    private void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        //helper.setFrom(NOREPLY_ADDRESS);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        //helper.addInline("attachment.png", resourceFile);
        emailSender.send(message);
    }
}
