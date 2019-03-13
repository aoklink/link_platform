package com.linkfeeling.api.controller.user;

import com.linkfeeling.api.comsumer.GymAccountServerDelegater;
import com.linkfeeling.common.controller.ControllerActionContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account/system_user")
public class ApiSystemUserController {

    @Autowired
    private GymAccountServerDelegater gymAccountServer;

    @PostMapping(value = {ControllerActionContract.OPERATE.ADD},produces="application/json;charset=UTF-8")
    public String add(@RequestBody String requestJson) {
        return gymAccountServer.systemUserAdd(requestJson).toString();
    }
    @PostMapping(value = {ControllerActionContract.OPERATE.UPDATE},produces="application/json;charset=UTF-8")
    public String update(@RequestBody String requestJson) {
        return gymAccountServer.systemUserUpdate(requestJson).toString();
    }
    @PostMapping(value = {ControllerActionContract.OPERATE.DELETE},produces="application/json;charset=UTF-8")
    public String delete(@RequestBody String requestJson) {
        return gymAccountServer.systemUserDelete(requestJson).toString();
    }
}
