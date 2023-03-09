package com.example.demo.web;

import com.example.demo.model.Notification;
import com.example.demo.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class NotificationAPI {

    private final NotificationService service;


    @GetMapping("/v1/all")
    public Mono<String> v1All(){

        return Mono.just("전체 메세지 발송");
    }


    @GetMapping("/v1/pub")
    public Mono<String> v1Pub(@RequestBody Map<String, String> request){
        Notification notification = Notification.builder()
                .senderID(request.get("sender"))
                .reciverID(request.get("reciver"))
                .content(request.get("content"))
                .build();

        service.onePublish(notification);
        return Mono.just("메세지 전송완료");
    }


    @GetMapping("/v1/sub/{userID}")
    public Flux<Notification> v1Sub(@PathVariable("userID") String userID){

        return service.subscription(userID)

                .doOnCancel(() -> service.cancle(userID));
    }

    private void sinkTest(FluxSink<?> sink){

    }
}
