package com.websocket.board.repo;

import com.websocket.board.model.kanban.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface TaskRepository extends MongoRepository<Task, String> {
}
