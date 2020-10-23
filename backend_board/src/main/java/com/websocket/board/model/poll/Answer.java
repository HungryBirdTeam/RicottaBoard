package com.websocket.board.model.poll;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.websocket.board.model.Channel;
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
public class Answer implements Serializable {

    @Id
    private String answerId;

    private String answer;
    private int voted;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "poll_id")
//    @JsonBackReference
    private Poll poll;
}
