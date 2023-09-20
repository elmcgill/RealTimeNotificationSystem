package com.elmcgill.services;

import com.elmcgill.dto.FollowUserDTO;
import com.elmcgill.models.Connection;
import com.elmcgill.models.User;
import com.elmcgill.repositories.ConnectionRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ConnectionService {

    private final UserService userService;
    private final ConnectionRepository connectionRepository;

    public ConnectionService(UserService userService, ConnectionRepository connectionRepository){
        this.userService = userService;
        this.connectionRepository = connectionRepository;
    }

    public void followUser(FollowUserDTO fuo){
        User followedUser = userService.getUserById(fuo.followed());
        User followingUser = userService.getUserById(fuo.following());
        Connection connection = new Connection(0L, followedUser, followingUser);

        connectionRepository.save(connection);
    }

    public Set<Connection> getFollowerList(User followedUser){
        return connectionRepository.findByFollowedUser(followedUser).orElse(new HashSet<>());
    }

}
