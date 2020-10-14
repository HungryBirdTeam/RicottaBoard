package com.websocket.board.service;

import com.websocket.board.model.Channel;
import com.websocket.board.model.Board;
import com.websocket.board.repo.ChannelRepository;
import com.websocket.board.repo.PostitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InitServiceImpl implements InitService {

    private final RedisTemplate redisTemplate;
    private final PostitRepository postitRepository;
    private final ChannelRepository channelRepository;

    @Override
    public Board getBoard(String channelId) {
        // 현재는 레디스에서 간헐적으로 DB에 저장되는 방식이 아니므로 레디스에서 받아오는 것은 보류
//        ValueOperations vop = redisTemplate.opsForValue();
//        SocketBoardMessage board = (SocketBoardMessage) vop.get(channelId);
//        if (board == null) {
//            Channel channel = channelRepository.findByChannelId(channelId);
//            vop.set(channelId, board);
//        }
        Channel channel = channelRepository.findByChannelId(channelId).get();
        //List<Postit> list = channelRepository.findByChannelId(channelId).getPostitList();
        Board board = new Board()
                .builder()
                .channelId(channelId)
                .postitList(channel.getPostitList())
                .idCount(channel.getIdCount())
                .build();
        //List<Postit> list = postitRepository.findAllByChannel(channelId);
        //System.out.println(list.toString());
//        Board board = new Board()
//                .builder()
//                .postitList(postitRepository.findAllByChannel(channelId))
//                .build();
        System.out.println(board);
        return board;
    }
}
