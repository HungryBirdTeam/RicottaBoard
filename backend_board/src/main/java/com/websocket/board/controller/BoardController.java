package com.websocket.board.controller;

import com.websocket.board.model.SocketBoardMessage;
import com.websocket.board.repo.ChannelRedisRepository;
import com.websocket.board.service.BoardService;
//import com.websocket.board.service.DBSyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Slf4j
@Controller
public class BoardController {

    //private final JwtTokenProvider jwtTokenProvider;
    private final ChannelRedisRepository channelRedisRepository;
    private final BoardService boardService;
    //private final DBSyncService dbSyncService;

    /**
     * websocket "/pub/board/message"로 들어오는 메시징을 처리한다.
     * 클라이언트로 부터 받은 보드 상태 전달
     */
    @MessageMapping("/board/message")
    public void message(SocketBoardMessage message) {
        //String nickname = jwtTokenProvider.getUserNameFromJwt(token);
        // 로그인 회원 정보로 대화명 설정
        //message.setSender(nickname);

        // Redis 세팅
        // 채널 인원수 세팅 -> 사실 지금 인원수는 의미없음
        message.setUserCount(channelRedisRepository.getUserCount(message.getChannelId()));
        // 레디스 보드 정보 업데이트
        channelRedisRepository.updateBoard(message);

        // Websocket에 발행된 메시지(클라이언트로 부터 받은 메시지)를 redis를 '통해서' 발행(publish)
        // 우리가 여기서 쓰는 레디스는 sub/pub 용도로 쓰는것이며
        // redisTemplate(레디스에서 제공하는 프로젝트내에서 쓰일 휘발성 객체) 를 통해서 Channel 정보를 저장하여 맞는 채널에 전달하는 것일뿐
        // 보드 자체가 가진 상태정보는 저장하고 있지 않음 -> 보드 상태를 저장하는 템플릿 생성해서 저장중
        // 받아온 상태를 그대로 전달하는 것이므로 레디스는 문제가 없으나 포스트잇 등 상태정보를 저장하기 위해서는 DB 로 트랜잭션이 있어야 함
        boardService.syncSocketBoardStatus(message);

        // RDB sync 로직 생성예정
        // dbSyncService.crudModuleSync(message.getCrudModule());

        //channelRedisRepository.findBoardByChannelId(message.getChannelId());
        // 채널 포스트잇 카운트 세팅
        // 채널 포스트잇 카운트가 레디스에 저장된 idCount 와 다르면?
        // DB 에 업데이트 & 레디스도 업데이트(채널 관련 레디스 업데이트는 여기밖에 없음)
//        if(message.getIdCount() != channelRedisRepository.findChannelById(message.getChannelId()).getIdCount()) {
//            dbSyncService.channelDBIdCountSync(message.getChannelId(), message.getIdCount());
//
//            Channel channel = channelRedisRepository.findChannelById(message.getChannelId());
//            channel.setIdCount(message.getIdCount());
//            channelRedisRepository.updateChannel(channel);
//        }

//        if(message.getIsDelete() && message.getDelete().getModuleName().equals("postit")) {
//            dbSyncService.postitDeleteSync(message);
//        } else {
//            dbSyncService.postitDBSync(message);
//        }
    }
}
