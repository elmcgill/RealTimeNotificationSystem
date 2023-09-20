package com.elmcgill.services;

import com.elmcgill.dto.Notification;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListeners {

    @KafkaListener(topics = "notifications", groupId = "com.elmcgill")
    public void consumeNotification(Notification notification){
        System.out.println(notification);
    }

}
