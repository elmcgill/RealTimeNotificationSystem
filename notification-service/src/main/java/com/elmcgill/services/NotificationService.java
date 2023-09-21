package com.elmcgill.services;

import com.elmcgill.dto.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class NotificationService {

    private final SimpMessagingTemplate template;

    @Autowired
    public NotificationService(SimpMessagingTemplate template){
        this.template = template;
    }

    public void sendNotification(Notification notification){
        Set<Long> users = notification.recipients();
        users.forEach(user -> template.convertAndSendToUser(""+user, "/post", notification.type()));
    }

}
