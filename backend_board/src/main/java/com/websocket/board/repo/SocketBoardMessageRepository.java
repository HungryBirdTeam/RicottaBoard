package com.websocket.board.repo;

import com.websocket.board.model.SocketBoardMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocketBoardMessageRepository extends MongoRepository<SocketBoardMessage, String> {
    Optional<SocketBoardMessage> findById(String id);
}
