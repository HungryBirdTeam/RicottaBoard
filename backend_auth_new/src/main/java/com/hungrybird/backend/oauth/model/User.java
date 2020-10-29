package com.hungrybird.backend.oauth.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

@Data
@Entity(name = "USER")
@Table(name = "user")
public class User {

    @Id
    @Column(name = "USER_ID")
    @SequenceGenerator(name = "user_seq", allocationSize = 1)
    private Long id;

    @NaturalId
    @Column(name = "EMAIL", unique = true)
    @NotBlank(message = "User email cannot be null")
    private String email;

    @Column(name = "USERNAME")
//    @Null(message = "User name cannot be null")
//    @NotBlank(message = "User name cannot be null")
    private String username;

    @Column(name = "PASSWORD")
    @NotNull(message = "Password cannot be null")
    private String password;

    @Column(name = "NICKNAME", unique=true)
    @NotNull(message = "Nickname cannot be null")
//    @NotBlank(message = "Nickname cannot be null")
    private String nickname;

    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean active;

    public User() { super(); }

    @Builder
    public User(User user) {
        id = user.getId();
        username = user.getUsername();
        password = user.getPassword();
        nickname = user.getNickname();
        email = user.getEmail();
        active = user.getActive();
        isEmailVerified = user.getEmailVerified();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email='" + email + '\'' + ", username='" + username + '\'' + ", password='"
                + password + '\'' + ", nickName='" + nickname + '\'' + ", active="
                + active + '}';
    }

//    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinTable(name = "USER_AUTHORITY", joinColumns = {
//            @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")}, inverseJoinColumns = {
//            @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")})
//    private Set<Role> roles = new HashSet<>();
//
    @Column(name = "IS_EMAIL_VERIFIED", nullable = false)
    private Boolean isEmailVerified;
//
//    @OneToMany(mappedBy = "user")
//    private List<Member> members = new ArrayList<>();

}
