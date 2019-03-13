package com.linkfeeling.api.controller.user;

import com.linkfeeling.api.comsumer.GymAccountServer;
import com.linkfeeling.api.comsumer.GymAccountServerDelegater;
import com.linkfeeling.common.controller.ControllerActionContract;
import com.linkfeeling.common.interactive.response.Response;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/account/gym_admin_user")
public class ApiGymAdminUserController {
    @Autowired
    private GymAccountServerDelegater gymAccountServer;

    @PostMapping(value={ControllerActionContract.OPERATE.ADD},produces="application/json;charset=UTF-8")
    public String gymAdminUserAdd(@RequestBody String requestJson) {
        return gymAccountServer.gymAdminUserAdd(requestJson).toString();
    }
    @PostMapping(value={ControllerActionContract.OPERATE.UPDATE},produces="application/json;charset=UTF-8")
    public String gymAdminUserUpdate(@RequestBody String requestJson) {
        return gymAccountServer.gymAdminUserUpdate(requestJson).toString();
    }
    @PostMapping(value={ControllerActionContract.OPERATE.DELETE},produces="application/json;charset=UTF-8")
    public String gymAdminUserDelete(@RequestBody String requestJson, HttpServletResponse httpServletResponse) {
        return gymAccountServer.gymAdminUserDelete(requestJson).toString();
    }

    @PostMapping(value={ControllerActionContract.OPERATE.GET_BIND},produces="application/json;charset=UTF-8")
    public String gymAdminUserBind(@RequestBody String requestJson) {
        return gymAccountServer.gymAdminUserGetByGymId(requestJson).toString();
    }
}
