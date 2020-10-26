package com.websocket.board.model.postit;

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
public class Postit implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(nullable = false, name = "postit_id")
    private Long id;

    private String title;
    private String contents;
//    @Column(name = "\"left\"")
    private String left;
//    @Column(name = "top")
    private String top;
//    @Column(name = "front_postit_id")
    private Long frontPostitId;
}
