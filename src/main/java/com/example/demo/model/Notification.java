package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    private String id;

    private String senderID;
    private String reciverID;
    private String content;

    private LocalDateTime regDate;


    @Builder
    public Notification(String senderID, String reciverID, String content){
        this.senderID = senderID;
        this.reciverID = reciverID;
        this.content = content;
        this.regDate = LocalDateTime.now();
    }

}
