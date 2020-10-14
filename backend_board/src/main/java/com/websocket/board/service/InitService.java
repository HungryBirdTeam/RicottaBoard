package com.websocket.board.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.websocket.board.model.Board;

public interface InitService {
    Board getBoard(String channelId) throws JsonProcessingException;

}
