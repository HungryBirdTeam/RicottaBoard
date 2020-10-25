package com.hungrybird.backend.oauth.service;

import com.hungrybird.backend.oauth.model.User;
import com.hungrybird.backend.oauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;



    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User save (User user){
        userRepository.save(user);
        return user;
    }


    public boolean createUser(User user){
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public Optional<User> getUser(Long id){
        return userRepository.findById(id);
    }

    //public Optional<User> getUserId(String email){
    public Optional<User> getUserId(String email){
        return userRepository.findByEmail(email);
    }

    public boolean deleteUser(Long id){
        final Optional<User> fetchedUser = userRepository.findById(id);
        if(fetchedUser.isPresent()){
            userRepository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }


}
