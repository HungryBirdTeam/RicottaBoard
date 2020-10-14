//package com.websocket.board.service;
//
//import com.websocket.board.model.Channel;
//import com.websocket.board.model.SocketBoardMessage;
//import com.websocket.board.model.scheduler.Scheduler;
//import com.websocket.board.model.crud.CRUDModule;
//import com.websocket.board.model.crud.CRUDType;
//import com.websocket.board.model.crud.ModuleType;
//import com.websocket.board.model.kanban.Kanban;
//import com.websocket.board.model.postit.Postit;
//import com.websocket.board.repo.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class DBSyncServiceImpl implements DBSyncService {
//
//    private final ChannelRepository channelRepository;
//    private final PostitRepository postitRepository;
//    private final SchedulerRepository schedulerRepository;
//    private final EventRepository eventRepository;
//    //private final PollRepository pollRepository;
//    private final KanbanRepository kanbanRepository;
//    private final TaskRepository taskRepository;
//
//    @Override
//    public void postitDBSync(SocketBoardMessage board) {
////        Channel channel = channelRepository.findByChannelId(board.getChannelId());
////        channel.setPostitList(board.getPostitList());
////        channelRepository.save(channel);
//        Postit postit = new Postit();
//        for (int i = 0; i < board.getPostitList().size(); i++) {
//            postit = board.getPostitList().get(i);
//            Optional<Postit> requestDbResult = postitRepository.findByFrontPostitId(postit.getFrontPostitId());
//            if(requestDbResult.isPresent()) {
//                postit.setId(requestDbResult.get().getId());
//            }
//            //?
//            postit.setChannel(channelRepository.findByChannelId(board.getChannelId()).get());
//            postitRepository.save(postit);
//        }
//    }
//
//    @Override
//    @Transactional
//    public void postitDeleteSync(SocketBoardMessage board) {
////        long id = postitRepository.findPostitByChannel_ChannelIdAndFrontPostitId(board.getChannelId(), board.getDelete().getId()).getId();
////        System.out.println(id);
////        postitRepository.deleteById(id);
//    }
//
//    @Override
//    public boolean crudModuleSync(CRUDModule crudModule) {
//
//        // String 검증 필요
//        ModuleType moduleType = crudModule.getModuleType();
//        CRUDType crudType = crudModule.getCrudType();
//        boolean ok = false;
//        switch (moduleType) {
//            case POSTIT:
//                ok = postitSync(crudType, (Postit) crudModule.getModuleObject());
//                break;
//            case SCHEDULER:
//                ok = schedulerSync(crudType, (Scheduler)crudModule.getModuleObject());
//                break;
////            case POLL:
////                ok = pollSync(crudType, (Poll)crudModule.getModuleObject());
////                break;
//            case KANBAN:
//                ok = kanbanSync(crudType, (Kanban)crudModule.getModuleObject());
//                break;
//        }
//        return ok;
//    }
//
//    @Override
//    @Transactional
//    public boolean postitSync(CRUDType crudType, Postit postit) {
//
//        boolean ok = false;
//        switch (crudType) {
//            case CREATE:
//                ok = postitRepository.save(postit) != null ? true : false;
//                break;
//            case UPDATE:
//                // 해당 포스트잇 아이디 찾아오는 코드 있어야
//                ok = postitRepository.save(postit) != null ? true : false;
//                break;
//            case DELETE:
//                postitRepository.delete(postit);
//                break;
//        }
//        return ok;
//    }
//
//    @Override
//    public boolean schedulerSync(CRUDType crudType, Scheduler scheduler) {
//        boolean ok = false;
//        switch (crudType) {
//            case CREATE:
//                ok = schedulerRepository.save(scheduler) != null ? true : false;
//                break;
//            case UPDATE:
//                // 해당 포스트잇 아이디 찾아오는 코드 있어야
//                ok = schedulerRepository.save(scheduler) != null ? true : false;
//                break;
//            case DELETE:
//                schedulerRepository.delete(scheduler);
//                break;
//        }
//        return ok;
//    }
//
////    @Override
////    public boolean pollSync(CRUDType crudType, Poll poll) {
////        boolean ok = false;
////        switch (crudType) {
////            case CREATE:
////                ok = pollRepository.save(poll) != null ? true : false;
////                break;
////            case UPDATE:
////                // 해당 포스트잇 아이디 찾아오는 코드 있어야
////                ok = pollRepository.save(poll) != null ? true : false;
////                break;
////            case DELETE:
////                pollRepository.delete(poll);
////                break;
////        }
////        return ok;
////    }
//
//    @Override
//    public boolean kanbanSync(CRUDType crudType, Kanban kanban) {
//        boolean ok = false;
//        switch (crudType) {
//            case CREATE:
//                ok = kanbanRepository.save(kanban) != null ? true : false;
//                break;
//            case UPDATE:
//                // 해당 포스트잇 아이디 찾아오는 코드 있어야
//                ok = kanbanRepository.save(kanban) != null ? true : false;
//                break;
//            case DELETE:
//                kanbanRepository.delete(kanban);
//                break;
//        }
//        return ok;
//    }
//
//    @Override
//    public void channelDBIdCountSync(String channelId, Long idCount) {
//
//        Channel channel = channelRepository.findByChannelId(channelId).get();
//        channel.setIdCount(idCount);
//
//        channelRepository.save(channel);
//    }
//
//}
