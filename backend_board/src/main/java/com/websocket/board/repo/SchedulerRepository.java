package com.websocket.board.repo;

import com.websocket.board.model.scheduler.Scheduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulerRepository extends JpaRepository<Scheduler, String> {
}
