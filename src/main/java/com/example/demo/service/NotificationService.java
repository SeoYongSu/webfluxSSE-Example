package com.example.demo.service;

import com.example.demo.model.Notification;
import com.example.demo.model.NotificationChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Flow;

@Service
@Slf4j
public class NotificationService {

    private Map<String, NotificationChannel> channels = new HashMap<>();


    /**
     * 특정 Target notification
     * @param notification
     */
    public void onePublish(Notification notification){
        Sinks.Many<Notification> sinks =
                channels.get(notification.getReciverID()) == null ? new NotificationChannel().getSink() : channels.get(notification.getReciverID()).getSink();

        sinks.tryEmitNext(notification);
        log.info("cnt  :  {}", sinks.currentSubscriberCount());
    }


    public Flux<Notification> subscription(String reciverID){
        if(channels.get(reciverID) == null){
            // 최초 구독 실행
            channels.put(reciverID, new NotificationChannel());
        }
        return channels.get(reciverID).asFlux();
    }
    

    /**
     * 구독 종료
     * @param userID
     */
    public void cancle(String userID){
        log.info("cancle 처리 ㄱㄱ  자원 해제");
        channels.remove(userID);
    }

}
