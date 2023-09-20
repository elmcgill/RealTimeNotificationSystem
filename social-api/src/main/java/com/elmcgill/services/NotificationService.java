package com.elmcgill.services;

import com.elmcgill.dto.Notification;
import com.elmcgill.dto.NotificationType;
import com.elmcgill.models.Connection;
import com.elmcgill.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final UserService userService;
    private final ConnectionService connectionService;

    private KafkaTemplate<String, Object> template;

    @Autowired
    public NotificationService(UserService userService, ConnectionService connectionService, KafkaTemplate<String, Object> template){
        this.userService = userService;
        this.connectionService = connectionService;
        this.template = template;
    }

    public void publishPostNotification(Post post){
        System.out.println(post.getAuthor());
        Set<Connection> connections = connectionService.getFollowerList(post.getAuthor());

        Set<Long> userIds = connections.stream()
                .map(connection -> connection.getFollowingUser().getUserId())
                .collect(Collectors.toSet());

        Notification notification = new Notification(NotificationType.POST, userIds);

        template.send("notifications", notification);
    }

}
