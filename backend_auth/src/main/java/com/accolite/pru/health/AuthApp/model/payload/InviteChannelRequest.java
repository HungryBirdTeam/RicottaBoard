package com.accolite.pru.health.AuthApp.model.payload;

import com.accolite.pru.health.AuthApp.model.User;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InviteChannelRequest implements Serializable {
    User user;
    String channelId;
}
