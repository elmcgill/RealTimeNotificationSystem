package com.elmcgill.controllers;

import com.elmcgill.dto.FollowUserDTO;
import com.elmcgill.services.ConnectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/connections")
public class ConnectionController {

    private final ConnectionService connectionService;

    public ConnectionController(ConnectionService connectionService){
        this.connectionService = connectionService;
    }

    @PostMapping("/follow")
    public ResponseEntity<String> createConnection(@RequestBody FollowUserDTO followUserObject){
        connectionService.followUser(followUserObject);

        return ResponseEntity.ok("Connection created <3");
    }

}
