package com.websocket.board.model.scheduler;

import com.websocket.board.model.Channel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@Entity
@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
//@JsonIgnoreProperties("events")
public class Scheduler implements Serializable {

    @Id
//    @Column(nullable = false, name = "scheduler_id")
    private String id;
//    @Column(name = "\"left\"")
    private String left;
    private String top;

//    @OneToMany(mappedBy = "scheduler")
//    @JsonManagedReference
//    @Builder.Default
    private List<Event> events = new ArrayList<>();

//    @OneToOne
//    @JoinColumn(name = "channel_id")
//    @JsonBackReference
    private Channel channel;
}
