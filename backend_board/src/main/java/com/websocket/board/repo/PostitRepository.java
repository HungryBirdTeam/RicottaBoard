package com.websocket.board.repo;

import com.websocket.board.model.postit.Postit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PostitRepository extends MongoRepository<Postit, Long> {
    List<Postit> findAllByChannel(String channelId);
}
