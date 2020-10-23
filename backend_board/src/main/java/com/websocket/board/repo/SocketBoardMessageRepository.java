package com.websocket.board.repo;

import com.websocket.board.model.SocketBoardMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SocketBoardMessageRepository extends MongoRepository<SocketBoardMessage, String> {
}
