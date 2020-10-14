package com.websocket.board.service;

import com.websocket.board.model.Channel;
import com.websocket.board.payload.CreateChannelRequest;
import com.websocket.board.payload.InviteChannelRequest;
import com.websocket.board.payload.WithdrawalRequest;

import java.util.List;

public interface ChannelService {
    Channel createChannel(CreateChannelRequest createChannelRequest);
    Boolean saveInvitedChannel(InviteChannelRequest inviteChannelRequest, String channelId);
    List<Channel> getMyChannels(String token);
    Boolean withdrawalChannel(WithdrawalRequest withdrawalRequest);
    List<String> getChannelMember(String channelId);
}
