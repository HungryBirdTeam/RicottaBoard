package com.accolite.pru.health.AuthApp.model.payload;


import com.accolite.pru.health.AuthApp.model.Role;
import com.accolite.pru.health.AuthApp.model.member.Member;
import com.accolite.pru.health.AuthApp.validation.annotation.NullOrNotBlank;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInfoRequest implements Serializable {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String id;
    private String email;
    private String username;
    private String password;
    private String nickname;
    private Boolean activate;
    @Builder.Default
    private List<Role> roles = new ArrayList<>();
    private Boolean emailVerified;

}
