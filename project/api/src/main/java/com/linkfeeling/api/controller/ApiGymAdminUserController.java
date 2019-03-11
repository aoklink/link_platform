package com.linkfeeling.api.controller;

import com.linkfeeling.api.comsumer.GymAccountServer;
import com.linkfeeling.common.controller.ControllerActionContract;
import com.linkfeeling.common.interactive.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account/gym_admin_user")
public class ApiGymAdminUserController {
    @Autowired
    private GymAccountServer gymAccountServer;

    @PostMapping(ControllerActionContract.OPERATE.LOGIN)
    public Response gymAdminUserLogin(@RequestBody String requestJson) {
        return gymAccountServer.gymAdminUserLogin(requestJson);
    }
    @PostMapping(ControllerActionContract.OPERATE.ADD)
    public Response gymAdminUserAdd(@RequestBody String requestJson) {
        return gymAccountServer.gymAdminUserAdd(requestJson);
    }
    @PostMapping(ControllerActionContract.OPERATE.UPDATE)
    public Response gymAdminUserUpdate(@RequestBody String requestJson) {
        return gymAccountServer.gymAdminUserUpdate(requestJson);
    }
    @PostMapping(ControllerActionContract.OPERATE.DELETE)
    public Response gymAdminUserDelete(@RequestBody String requestJson) {
        return gymAccountServer.gymAdminUserDelete(requestJson);
    }
}
