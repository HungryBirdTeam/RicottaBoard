package com.websocket.board.repo;

import com.websocket.board.model.kanban.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StateRepository extends MongoRepository<State, String> {
}
