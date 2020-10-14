package com.websocket.board.model.scheduler;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.websocket.board.model.Channel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
//@JsonIgnoreProperties("events")
public class Scheduler implements Serializable {

    @Id
    @Column(nullable = false, name = "scheduler_id")
    private String id;
    @Column(name = "\"left\"")
    private String left;
    private String top;

    @OneToMany(mappedBy = "scheduler")
    @JsonManagedReference
    @Builder.Default
    private List<Event> events = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "channel_id")
    @JsonBackReference
    private Channel channel;
}
