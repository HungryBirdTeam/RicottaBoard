package com.websocket.board.repo;

import com.websocket.board.model.kanban.Kanban;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KanbanRepository extends JpaRepository<Kanban, String> {
}
