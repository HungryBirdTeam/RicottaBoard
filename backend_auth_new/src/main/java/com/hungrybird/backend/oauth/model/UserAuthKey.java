package com.hungrybird.backend.oauth.model;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name= "userAuthKey")
public class UserAuthKey {

    @Id
    @Column(name = "USER_ID")
//    @ForeignKey(value = "user.USER_ID")
    @JoinColumn(name = "USER_ID")
    private Long id;

    @Column(name= "AuthKey")
    private String authKey;

    public UserAuthKey() {
    }

    @Builder
    public UserAuthKey(Long id, String authKey) {
        this.id = id;
        this.authKey = authKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }
}
