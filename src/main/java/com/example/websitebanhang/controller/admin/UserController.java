package com.example.websitebanhang.controller.admin;

import com.example.websitebanhang.dto.MessageResponse;
import com.example.websitebanhang.dto.user.JwtResponse;
import com.example.websitebanhang.dto.user.LoginDTO;
import com.example.websitebanhang.dto.user.SignupRequest;
import com.example.websitebanhang.entity.User;
import com.example.websitebanhang.registration.OnRegistrationCompleteEvent;
import com.example.websitebanhang.service.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    final IUserService userService;

    private final MessageSource messages;

    final  ApplicationEventPublisher eventPublisher;

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    public UserController(IUserService userService, MessageSource messages, ApplicationEventPublisher eventPublisher) {
        this.userService = userService;
        this.messages = messages;
        this.eventPublisher = eventPublisher;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO) throws JsonProcessingException {
        JwtResponse jwtResponse = userService.login(loginDTO);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest, HttpServletRequest request) throws Exception {

        User user = userService.registerNewUserAccount(signUpRequest);

        String appUrl = request.getContextPath();
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user,request.getLocale(), appUrl));


        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @GetMapping("/regitrationConfirm")
    public String confirmRegistration
            (WebRequest request, Model model, @RequestParam("token") String token) {

        LOGGER.trace("=================getAppUrl======================");
        LOGGER.info(getAppUrl((HttpServletRequest) request));
        LOGGER.trace("=======================================");

//        Locale locale = request.getLocale();
//
//        VerificationToken verificationToken = userService.getVerificationToken(token);
//        if (verificationToken == null) {
//            String message = messages.getMessage("auth.message.invalidToken", null, locale);
//            model.addAttribute("message", message);
//            return "redirect:/badUser.html?lang=" + locale.getLanguage();
//        }
//
//        User user = verificationToken.getUser();
//        Calendar cal = Calendar.getInstance();
//        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
//            String messageValue = messages.getMessage("auth.message.expired", null, locale);
//            model.addAttribute("message", messageValue);
//            return "redirect:/badUser.html?lang=" + locale.getLanguage();
//        }
//
//        user.setEnabled(true);
//        userService.saveRegisteredUser(user);
//        return "redirect:/login.html?lang=" + request.getLocale().getLanguage();
        return  "1";
    }

}
