package com.hungrybird.backend.oauth.service;

import com.hungrybird.backend.oauth.model.User;
import com.hungrybird.backend.oauth.model.UserAuthKey;
import com.hungrybird.backend.oauth.model.payload.ApiResponce;
import com.hungrybird.backend.oauth.repository.UserAuthKeyRepository;
import com.hungrybird.backend.oauth.repository.UserRepository;
import com.hungrybird.backend.oauth.util.JwtUtil;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserAuthKeyRepository userAuthKeyRepository;
    private final JwtUtil jwtUtil;



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
        StringBuilder buffer = new StringBuilder();
        int num;

        while(buffer.length() < size) {
            num = random.nextInt(10);
            buffer.append(num);
        }

        return buffer.toString();
    }

    @Autowired
    public UserService(UserRepository userRepository,
                       UserAuthKeyRepository userAuthKeyRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.userAuthKeyRepository = userAuthKeyRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public User save (User user){
        userRepository.save(user);
        return user;
    }


    public boolean createUser(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
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
    public Optional<User> getUser(String email) { return userRepository.findByEmail(email);}
    //public Optional<User> getUserId(String email){
    public long getUserId(String email){
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            return user.get().getId();
        }

        return -1;
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

    public boolean checkVerify(String email, String authKey){

        long id = getUserId(email);

        Optional<UserAuthKey> dataForCheckVerify = userAuthKeyRepository.findById(id);

        if(dataForCheckVerify.isPresent()){
            boolean match = dataForCheckVerify.get().getAuthKey().equals(authKey);

            if(match){
                User userForSetVerify = userRepository.getOne(id);
//                userForSetVerify.setActive(true);
                userForSetVerify.setEmailVerified(true);
                userRepository.save(userForSetVerify);
                return true;
            }

            return false;
        }


        return false;
    }

    public ResponseEntity testLogin(String email, String password){
        Optional<User> user = getUser(email);

        if(!user.isPresent()){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }


//        boolean isCorrect = password.equals(user.get().getPassword());

        boolean isCorrect = passwordEncoder.matches(password,user.get().getPassword());

        if(isCorrect){


            long id = user.get().getId();
            String name = user.get().getUsername();

            String accessToken = jwtUtil.createToken(id,name);
            System.out.println("AccessToken is : " + accessToken);
            return ResponseEntity.ok(new ApiResponce(true, "Login Success. : " + accessToken));
        }

        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    public boolean logout(){

        return true;
    }


}
