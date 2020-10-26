package com.websocket.board.model.scheduler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Scheduler implements Serializable {

    private String id;
    private String left;
    private String top;

    private List<Event> events = new ArrayList<>();
}
