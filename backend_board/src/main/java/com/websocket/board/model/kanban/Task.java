package com.websocket.board.model.kanban;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;

//@Entity
@Document
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Task implements Serializable {

    @Id
//    @Column(nullable = false, name = "task_id")
    private String id;
    private String taskTitle;
    private String taskContents;
    private String taskAssigner;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "state_id")
//    @JsonBackReference
    private State state;
}
