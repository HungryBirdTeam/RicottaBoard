package com.websocket.board.model.scheduler;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Event implements Serializable {

    @Id
    @Column(nullable = false, name = "event_id")
    private String id;
    private String name;
    private String content;
    private String start;
    private String end;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scheduler_id")
    @JsonBackReference
    private Scheduler scheduler;
}
