package com.example.websitebanhang.registration.listener;

import com.example.websitebanhang.email.EmailService;
import com.example.websitebanhang.entity.User;
import com.example.websitebanhang.registration.OnRegistrationCompleteEvent;
import com.example.websitebanhang.service.IUserService;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final IUserService userService;

    private final MessageSource messages;

    private final EmailService emailService;

    public RegistrationListener(IUserService userService, MessageSource messages, EmailService emailService) {
        this.userService = userService;
        this.messages = messages;
        this.emailService = emailService;
    }

    @SneakyThrows
    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) throws MessagingException {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = event.getAppUrl() + "/regitrationConfirm.html?token=" + token;

        String message = messages.getMessage("message.regSucc", null,"You registered successfully. To confirm your registration, please click on the below link.", event.getLocale());
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("url", "http://localhost:8080" + confirmationUrl);
        objectMap.put("message",  message);
       /* emailService.sendSimpleMessage(recipientAddress,subject,
                message + "\r\n" + "http://localhost:8080" + confirmationUrl );*/

        emailService.sendMessageUsingThymeleafTemplate(recipientAddress, subject, objectMap);


    }

}