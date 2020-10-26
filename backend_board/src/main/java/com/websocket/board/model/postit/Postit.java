package com.websocket.board.model.postit;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Postit implements Serializable {

    private Long id;

    private String title;
    private String contents;
    private String left;
    private String top;
    private Long frontPostitId;
}
