package com.elmcgill.services;

import com.elmcgill.dto.Notification;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListeners {

    private final NotificationService notificationService;

    public NotificationListeners(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "notifications", groupId = "com.elmcgill")
    public void consumeNotification(Notification notification){
        notificationService.sendNotification(notification);
    }

}
