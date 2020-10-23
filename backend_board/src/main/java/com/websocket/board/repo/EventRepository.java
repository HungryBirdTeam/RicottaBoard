package com.websocket.board.repo;

import com.websocket.board.model.scheduler.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface EventRepository extends MongoRepository<Event, String> {
}
