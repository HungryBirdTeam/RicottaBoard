package com.websocket.board.repo;

import com.websocket.board.model.postit.Postit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostitRepository extends JpaRepository<Postit, Long> {
    List<Postit> findAllByChannel(String channelId);
    Optional<Postit> findByFrontPostitId(Long frontPostitId);
    Postit findPostitByChannel_ChannelIdAndFrontPostitId(String channel_channelId, Long frontPostitId);
}
