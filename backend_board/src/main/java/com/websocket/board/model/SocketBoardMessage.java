package com.websocket.board.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.websocket.board.model.kanban.Kanban;
import com.websocket.board.model.poll.Poll;
import com.websocket.board.model.postit.Postit;
import com.websocket.board.model.scheduler.Scheduler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Document(collection = "channel")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocketBoardMessage implements Serializable {

    // 채널 자체 정보
    @Id
    @JsonProperty(value = "channelId")
    private String id;       // 채널 아이디
    private Long userCount;         // 현재 참여하고 있는 사용자 수
    private List<String> memberList;// 채널 참여 멤버 닉네임 리스트

    // 모듈 관련 정보
    private Long idCount;           // 프론트 생성 아이디 카운트
    private List<Postit> postitList;// 포스트잇 리스트 객체
    private Kanban kanban;          // 칸반 객체
    private Scheduler scheduler;    // 스케줄러 객체
    private List<Poll> poll;        // 투표 리스트 객체
}
