package com.websocket.board.model.kanban;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@Entity
@Document
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
//@JsonIgnoreProperties("tasks")
public class State implements Serializable {

    @Id
//    @Column(nullable = false, name = "state_id")
    private String id;
    private String columnTitle;

//    @OneToMany(mappedBy = "state")
//    @JsonManagedReference
//    @Builder.Default
    private List<Task> tasks = new ArrayList<>();

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "kanban_id")
//    @JsonBackReference
    private Kanban kanban;
}
