package com.hungrybird.backend.oauth.controller;

import com.hungrybird.backend.oauth.model.User;
import com.hungrybird.backend.oauth.model.payload.ApiResponce;
import com.hungrybird.backend.oauth.model.payload.LoginRequest;
import com.hungrybird.backend.oauth.service.MailService;
import com.hungrybird.backend.oauth.service.UserService;
import com.hungrybird.backend.oauth.util.JwtUtil;
import freemarker.template.TemplateException;
import io.jsonwebtoken.Jwt;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@RestController
//@Controller
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private final MailService mailService;

    private final JwtUtil jwtUtil;

    @Autowired
    public UserController(UserService userService,
                          JwtUtil jwtUtil, MailService mailService){
        this.userService = userService;
        this.mailService = mailService;
        this.jwtUtil = jwtUtil;
    }

    @ApiOperation("Create User Info")
    @PostMapping("/user")
    public ResponseEntity createUser(@RequestBody User user, UriComponentsBuilder builder) throws IOException, TemplateException, MessagingException {

        boolean flag = userService.createUser(user);
        if (!flag) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        String to = user.getEmail();

        mailService.mailSend(to);

        return ResponseEntity.ok(new ApiResponce(true, "User create req Success. Check your email for verification"));
    }

    @ApiOperation("Delete User Info")
    @DeleteMapping("/user")
    public ResponseEntity deleteUser(@RequestParam String email, @RequestParam String password){

        Optional<User> data = userService.getUser(email);
        User user;
        if(data.isPresent()){
            user = data.get();

            if(user.getPassword().matches(password)){
                userService.deleteUser(user.getId());
                return new ResponseEntity<>(HttpStatus.OK);

            } else return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiOperation("Verify User")
    @PostMapping("/verifyUser")
    public ResponseEntity<Void> verifyUser(@RequestParam String email, @RequestParam String authKey) {

        System.out.println("got Email : " + email);
        System.out.println("got authKey : " + authKey);


        boolean isAvailable = userService.checkVerify(email, authKey);

        if(isAvailable){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiOperation("Login User")
    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestParam LoginRequest loginRequest){



        return new ResponseEntity(HttpStatus.OK);
    }


    @ApiOperation(" Login User Test")
    @PostMapping("/TestLogin")
    public ResponseEntity login(@RequestParam String email, @RequestParam String password){

        System.out.println("Req Email : " + email);
//        System.out.println("Req Password : " + passwordEncoder.encode(password));





        return userService.testLogin(email, password);

//        return new ResponseEntity.ok().body;
    }

    @ApiOperation("Logout User Test")
    @GetMapping("/logout")
    public ResponseEntity logout(){

        return new ResponseEntity(HttpStatus.OK);
    }


//    @PostMapping("/user")
//    public ResponseEntity<User> save (User user){
//        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
//    }


}
