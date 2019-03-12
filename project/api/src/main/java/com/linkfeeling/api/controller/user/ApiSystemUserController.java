package com.linkfeeling.api.controller.user;

import com.linkfeeling.api.comsumer.GymAccountServer;
import com.linkfeeling.common.controller.ControllerActionContract;
import com.linkfeeling.common.interactive.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account/system_user")
public class ApiSystemUserController {

    @Autowired
    private GymAccountServer gymAccountServer;

    @PostMapping(ControllerActionContract.OPERATE.ADD)
    public Response add(@RequestBody String requestJson) {
        return gymAccountServer.systemUserAdd(requestJson);
    }
    @PostMapping(ControllerActionContract.OPERATE.UPDATE)
    public Response update(@RequestBody String requestJson) {
        return gymAccountServer.systemUserUpdate(requestJson);
    }
    @PostMapping(ControllerActionContract.OPERATE.DELETE)
    public Response delete(@RequestBody String requestJson) {
        return gymAccountServer.systemUserDelete(requestJson);
    }
}
