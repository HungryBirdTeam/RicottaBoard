package com.websocket.board.repo;

import com.websocket.board.model.kanban.Kanban;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface KanbanRepository extends MongoRepository<Kanban, String> {
}
