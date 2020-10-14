package com.websocket.board.repo;

import com.websocket.board.model.Channel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChannelRepository extends CrudRepository<Channel, Long> {
    Optional<Channel> findByChannelId(String channelId);
}
