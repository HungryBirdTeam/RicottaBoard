package com.websocket.board.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.websocket.board.model.SocketBoardMessage;
import com.websocket.board.repo.ChannelRedisRepository;
import com.websocket.board.service.BoardClientService;
import com.websocket.board.service.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardInitController {

    //@Autowired
    //InitService initService;
    private final ChannelRedisRepository channelRedisRepository;
    private final ChannelService channelService;
    private final BoardClientService boardClientService;

    @CrossOrigin("*")
    @GetMapping("/board/{channelId}")
    public SocketBoardMessage getBoardStatusInit(
            @RequestHeader(name = "Authorization") String Authorization,
            @PathVariable("channelId") String channelId)
            throws JsonProcessingException {

        SocketBoardMessage socketBoardMessage = channelRedisRepository.findBoardByChannelId(channelId);

        if (boardClientService.checkToken(Authorization).getIsValid()) {
            socketBoardMessage.setMemberList(channelService.getChannelMember(channelId));
        }
        // 이 부분 레디스에 정보가 없다면 디비에서 가져오도록 수정할 예정
//        if(socketBoardMessage == null) {
//            initService.getBoard(channelId);
//        }

        return socketBoardMessage;
    }

    @CrossOrigin("*")
    @GetMapping("/board/tutorial/{channelId}")
    public SocketBoardMessage getBoardStatusInit(
            @PathVariable("channelId") String channelId)
            throws JsonProcessingException {

        SocketBoardMessage socketBoardMessage = channelRedisRepository.findBoardByChannelId(channelId);

        if (socketBoardMessage == null) {
            channelRedisRepository.createChannel("Tutorial Channel s2", "earlyBird10TeamTestChannel1");
            socketBoardMessage = channelRedisRepository.findBoardByChannelId(channelId);
        }

        if (channelId.equals("earlyBird10TeamTestChannel1")) {
            List<String> tutorialMockMember = new ArrayList<>();
            tutorialMockMember.add("배민규");
            tutorialMockMember.add("정용우");
            tutorialMockMember.add("김강현");
            tutorialMockMember.add("김동률");
            tutorialMockMember.add("배재원");
            tutorialMockMember.add("최문경");

            socketBoardMessage.setMemberList(tutorialMockMember);
        }

        return socketBoardMessage;
    }

}
