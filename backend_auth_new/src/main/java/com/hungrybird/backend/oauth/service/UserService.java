package com.hungrybird.backend.oauth.service;

import com.hungrybird.backend.oauth.model.User;
import com.hungrybird.backend.oauth.model.UserAuthKey;
import com.hungrybird.backend.oauth.repository.UserAuthKeyRepository;
import com.hungrybird.backend.oauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserAuthKeyRepository userAuthKeyRepository;


    private int size = 10;      // default authkey size = 10
    //인증키 생성
    private String getKey(int size) {
        this.size = size;
        return getAuthCode();
    }
    private String getKey() {
        return getAuthCode();
    }

    //인증코드 난수 발생
    private String getAuthCode() {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        int num = 0;

        while(buffer.length() < size) {
            num = random.nextInt(10);
            buffer.append(num);
        }

        return buffer.toString();
    }

    @Autowired
    public UserService(UserRepository userRepository, UserAuthKeyRepository userAuthKeyRepository){
        this.userRepository = userRepository;
        this.userAuthKeyRepository = userAuthKeyRepository;
    }

    public User save (User user){
        userRepository.save(user);
        return user;
    }


    public boolean createUser(User user){
        try {
            userRepository.save(user);
            userAuthKeyRepository.save(new UserAuthKey(user.getId(), getKey()));
            System.out.println(userAuthKeyRepository.getOne(user.getId()).getAuthKey());
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
