package com.websocket.board.repo;

import com.websocket.board.model.kanban.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, String> {
}
