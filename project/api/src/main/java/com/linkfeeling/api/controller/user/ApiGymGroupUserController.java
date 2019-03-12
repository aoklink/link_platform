package com.linkfeeling.api.controller.user;

import com.linkfeeling.api.comsumer.GymAccountServer;
import org.springframework.beans.factory.annotation.Autowired;

//@RestController
//@RequestMapping("/api/account/gym_group_user")
// 以后增加连锁店功能时再使用
public class ApiGymGroupUserController {
    @Autowired
    private GymAccountServer gymAccountServer;
}