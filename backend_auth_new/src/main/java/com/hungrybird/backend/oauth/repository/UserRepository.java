package com.hungrybird.backend.oauth.repository;

import com.hungrybird.backend.oauth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Optional<User> findByNickname(String nickname);

    Boolean existsByUsername(String username);

    int deleteByEmailAndPassword(String email, String password);

    int deleteByEmail(String email);
}
