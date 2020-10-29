package com.hungrybird.backend.oauth.controller;

import com.hungrybird.backend.oauth.model.Mail;
import com.hungrybird.backend.oauth.model.User;
import com.hungrybird.backend.oauth.service.MailService;
import com.hungrybird.backend.oauth.service.UserService;
import freemarker.template.TemplateException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Optional;

@RestController
//@Controller
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;



    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder, MailService mailService){
        this.userService = userService;
        this.mailService = mailService;
        this.passwordEncoder = passwordEncoder;
    }

    @ApiOperation("Create User Info")
    @PostMapping("/user")
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder builder) throws IOException, TemplateException, MessagingException {
        System.out.print(user.toString());
        System.out.println("Get Req");
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        boolean flag = userService.createUser(user);
        if (!flag) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        String to = user.getEmail();

        mailService.mailSend(to);
        System.out.println("Send Auth Test Email");

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
//

    @ApiOperation("Delete User Info")
    @DeleteMapping("/user")
    public ResponseEntity deleteUser(@RequestParam String email, @RequestParam String password){
System.out.println("asdf");
        Optional<User> data = userService.getUserId(email);
        User user;
        if(data.isPresent()){
            user = data.get();

            if(user.getPassword().matches(password)){
                userService.deleteUser(user.getId());
                return new ResponseEntity<Void>(HttpStatus.OK);

            } else return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ApiOperation("Verify User")
    @PostMapping("/verifyUser")
    public ResponseEntity verifyUser(@RequestParam String email, @RequestParam String authKey) {



        return new ResponseEntity(HttpStatus.OK);
    }





//    @PostMapping("/user")
//    public ResponseEntity<User> save (User user){
//        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
//    }
}
