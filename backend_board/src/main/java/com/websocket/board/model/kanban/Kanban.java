package com.websocket.board.model.kanban;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.websocket.board.model.Channel;
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
//@JsonIgnoreProperties("states")
public class Kanban implements Serializable {

    @Id
    private String id;
    private String kanbanName;
//    @Column(name = "\"left\"")
    private String left;
    private String top;

//    @OneToMany(mappedBy = "kanban")
//    @JsonManagedReference
//    @Builder.Default
    private List<State> states = new ArrayList<>();

//    @OneToOne
//    @JoinColumn(name = "channel_id")
//    @JsonBackReference
    private Channel channel;
}
