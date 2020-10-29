package com.hungrybird.backend.oauth.repository;

import com.hungrybird.backend.oauth.model.UserAuthKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthKeyRepository extends JpaRepository<UserAuthKey, Long> {
    Optional<UserAuthKey> findById(Long id);
}
