package com.hungrybird.backend.oauth.service;

import com.hungrybird.backend.oauth.model.Mail;
import com.hungrybird.backend.oauth.model.User;
import com.hungrybird.backend.oauth.model.UserAuthKey;
import com.hungrybird.backend.oauth.repository.UserAuthKeyRepository;
import com.hungrybird.backend.oauth.repository.UserRepository;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;

@Service
public class MailService {
    private final JavaMailSender mailSender;
    private final Configuration templateConfiguration;
    private final UserAuthKeyRepository userAuthKeyRepository;
    private final UserRepository userRepository;

//    @Value("${app.velocity.templates.location}")
    private String basePackagePath = "/template/";

    @Value("${spring.mail.username}")
    private String FROM_ADDRESS;

    @Value("${server.address}")
    private String baseUrl;

    @Autowired
    public MailService(JavaMailSender mailSender,
                       Configuration templateConfiguration,
                       UserAuthKeyRepository userAuthKeyRepository,
                       UserRepository userRepository) {
        this.mailSender = mailSender;
        this.templateConfiguration = templateConfiguration;
        this.userAuthKeyRepository = userAuthKeyRepository;
        this.userRepository = userRepository;

    }






    public void mailSend( String to) throws IOException, TemplateException, MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        Optional<User> user = userRepository.findByEmail(to);

        System.out.println("user name is : " + user.get().getUsername());
        long tempId = user.get().getId();
        Optional<UserAuthKey> data = userAuthKeyRepository.findById(tempId);
        String temp = "nope";

        if(data.isPresent()){
            temp = data.get().getAuthKey();
        }
        System.out.println("AuthKey is " + temp);

        String authKey =
                userAuthKeyRepository.findById(user.get().getId()).get().getAuthKey();

        Mail mail = new Mail();
        mail.setSubject("[이메일 인증] 리코타보드 회원 인증을 해주세요!");
        mail.getModel().put("userName", user.get().getUsername());
        mail.getModel().put("userEmailVerifyKey", authKey);
        mail.getModel().put("userEmail", user.get().getEmail());
        mail.getModel().put("baseUrl", baseUrl);
        mail.setTo(to);
        mail.setFrom(FROM_ADDRESS);

        templateConfiguration.setClassForTemplateLoading(getClass(), basePackagePath);
        Template template = templateConfiguration.getTemplate("email-check.ftl");
        String mailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getModel());
        mail.setContent(mailContent);

        send(mail);

    }

    public void send(Mail mail) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(mail.getTo());
        helper.setText(mail.getContent(), true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());
        mailSender.send(message);
    }

}
