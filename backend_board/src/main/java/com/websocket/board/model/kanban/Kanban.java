package com.websocket.board.model.kanban;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Kanban implements Serializable {

    private String id;
    private String kanbanName;
    private String left;
    private String top;
    
    private List<State> states = new ArrayList<>();
}
