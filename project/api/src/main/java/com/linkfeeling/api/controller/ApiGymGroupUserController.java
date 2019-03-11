package com.linkfeeling.api.controller;

import com.linkfeeling.api.comsumer.GymAccountServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account/gym_group_user")
public class ApiGymGroupUserController {
    @Autowired
    private GymAccountServer gymAccountServer;
}
