package com.websocket.board.controller;

import com.websocket.board.model.SocketBoardMessage;
import com.websocket.board.repo.ChannelRedisRepository;
import com.websocket.board.repo.SocketBoardMessageRepository;
import com.websocket.board.service.BoardService;
//import com.websocket.board.service.DBSyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Controller
public class BoardController {

    private final ChannelRedisRepository channelRedisRepository;
    private final SocketBoardMessageRepository socketBoardMessageRepository;
    private final BoardService boardService;

    /**
     * websocket "/pub/board/message"로 들어오는 메시징을 처리한다.
     * 클라이언트로 부터 받은 보드 상태 전달
     */
    @MessageMapping("/board/message")
    public void message(SocketBoardMessage message) {

        // Redis 세팅
        // 채널 인원수 세팅
        message.setUserCount(channelRedisRepository.getUserCount(message.getId()));
        // 레디스 보드 정보 업데이트
        channelRedisRepository.updateBoard(message);
        // DB에 저장
        socketBoardMessageRepository.save(message);
//        Optional<SocketBoardMessage> socketBoardMessage = socketBoardMessageRepository.findByChannelId(message.getChannelId());
//        if(socketBoardMessage.isPresent()) {
//            socketBoardMessageRepository.save(new SocketBoardMessage
//                    .builder()
//                    .build());
//        }
        // 받아온 메시지 발송
        boardService.syncSocketBoardStatus(message);
    }
}
