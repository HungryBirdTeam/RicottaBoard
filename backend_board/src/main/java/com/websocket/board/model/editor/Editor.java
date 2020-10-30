package com.websocket.board.model.editor;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Editor implements Serializable{
    private String title;
    private String text;
    private String left;
    private String top;
    private Long mdId;
}

