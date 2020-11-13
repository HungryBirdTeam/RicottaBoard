package com.hungrybird.back.AuthApp.event;

import com.hungrybird.back.AuthApp.model.member.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Getter
@Setter
public class OnInvitationCompleteEvent  extends ApplicationEvent {
    private transient UriComponentsBuilder redirectUrl;
    private List<Member> member;
    private String channelId;
    private List<String> email;

    public OnInvitationCompleteEvent(List<String> email, String channelId,List<Member> member, UriComponentsBuilder redirectUrl) {
        super(member);
        this.member = member;
        this.redirectUrl = redirectUrl;
        this.channelId = channelId;
        this.email = email;
    }

}
