package com.hungrybird.backend.oauth.controller;

import com.hungrybird.backend.oauth.model.User;
import com.hungrybird.backend.oauth.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
//@Controller
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @ApiOperation("Create User Info")
    @PostMapping("/user")
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder builder) {
        System.out.print(user.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        boolean flag = userService.createUser(user);
        if (!flag) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
//
    @ApiOperation("Delete User Info")
    @DeleteMapping("/user")
    public ResponseEntity deleteUser(@RequestParam String email, @RequestParam String password){

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





//    @PostMapping("/user")
//    public ResponseEntity<User> save (User user){
//        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
//    }
}
