package com.example.demo.model;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.Map;

@Component
public class NotificationChannel {
    private final Sinks.Many<Notification> notificationEvent;

    /**
     * 생성자
     */
    public NotificationChannel() {
        this.notificationEvent = Sinks.many().multicast().onBackpressureBuffer();
    }

    public Sinks.Many<Notification> getSink() {
        return this.notificationEvent;
    }
    public Flux<Notification> asFlux() {
        return this.notificationEvent.asFlux();
    }

}
