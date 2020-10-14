//package com.websocket.board.service;
//
//import com.websocket.board.model.SocketBoardMessage;
//import com.websocket.board.model.scheduler.Scheduler;
//import com.websocket.board.model.crud.CRUDModule;
//import com.websocket.board.model.crud.CRUDType;
//import com.websocket.board.model.kanban.Kanban;
//import com.websocket.board.model.postit.Postit;
//
//public interface DBSyncService {
//
//    void postitDBSync(SocketBoardMessage board);
//    void postitDeleteSync(SocketBoardMessage board);
//
//    boolean crudModuleSync(CRUDModule crudModule);
//    boolean postitSync(CRUDType crudType, Postit postit);
//    boolean schedulerSync(CRUDType crudType, Scheduler scheduler);
//    //boolean pollSync(CRUDType crudType, Poll poll);
//    boolean kanbanSync(CRUDType crudType, Kanban kanban);
//
//    void channelDBIdCountSync(String channelId, Long idCount);
//
//}
