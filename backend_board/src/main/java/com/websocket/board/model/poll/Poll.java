package com.websocket.board.model.poll;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.websocket.board.model.Channel;
import com.websocket.board.model.postit.Postit;
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
//@JsonIgnoreProperties("answers")
public class Poll implements Serializable {

    @Id
    private String pollId;

//    @Column(name = "\"left\"")
    private String left;
    private String top;
    private String question;

//    @OneToMany(mappedBy = "poll")
//    @JsonManagedReference
//    @Builder.Default
    private List<Answer> answers = new ArrayList<>();

    private boolean multipleVotes;
    private int totalVotes;

    //@OneToMany(mappedBy = "poll")
    //@JsonManagedReference
    //@Builder.Default
//    @Transient
    private List<String> userVoted = new ArrayList<>();

    private boolean isSetAll;
    private boolean isEnd;

}
