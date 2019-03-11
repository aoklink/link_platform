package com.linkfeeling.api.comsumer;

import com.linkfeeling.common.controller.ControllerActionContract;
import com.linkfeeling.common.interactive.response.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface GymAccountServer {
    @PostMapping("/account/system_user"+ ControllerActionContract.OPERATE.VERIFY)
    // {"phone":"13012341234","password":"md5(psw)"}
    Response systemUserLogin(@RequestBody String requestJson);

    @PostMapping("/account/gym_admin_user"+ ControllerActionContract.OPERATE.VERIFY)
    // {"phone":"13012341234","password":"md5(psw)"}
    Response gymAdminUserLogin(@RequestBody String requestJson);

    @PostMapping("/account/gym_group_user"+ ControllerActionContract.OPERATE.VERIFY)
    // {"phone":"13012341234","password":"md5(psw)"}
    Response gymGroupUserLogin(@RequestBody String requestJson);

    @PostMapping("/account/system_user"+ ControllerActionContract.OPERATE.ADD)
    // {name:"system_user","phone":"13012341234","password":"psw"}
    Response systemUserAdd(@RequestBody String requestJson);

    @PostMapping("/account/gym_admin_user"+ ControllerActionContract.OPERATE.ADD)
    // {name:"system_user","phone":"13012341234","password":"psw"}
    Response gymAdminUserAdd(@RequestBody String requestJson);

    @PostMapping("/account/gym_group_user"+ ControllerActionContract.OPERATE.ADD)
    // {name:"system_user","phone":"13012341234","password":"psw"}
    Response gymGroupUserAdd(@RequestBody String requestJson);

    @PostMapping("/account/system_user"+ ControllerActionContract.OPERATE.UPDATE)
    // {id:1,name:"system_user","phone":"13012341234","password":"psw"}
    Response systemUserUpdate(@RequestBody String requestJson);

    @PostMapping("/account/gym_admin_user"+ ControllerActionContract.OPERATE.UPDATE)
    // {id:1,name:"system_user","phone":"13012341234","password":"psw"}
    Response gymAdminUserUpdate(@RequestBody String requestJson);

    @PostMapping("/account/gym_admin_user"+ ControllerActionContract.OPERATE.UPDATE_GROUP)
        // {id:1,name:"system_user","phone":"13012341234","password":"psw"}
    Response gymAdminUserUpdateGroup(@RequestBody String requestJson);

    @PostMapping("/account/gym_group_user"+ ControllerActionContract.OPERATE.UPDATE)
    // {id:1,name:"system_user","phone":"13012341234","password":"psw"}
    Response gymGroupUserUpdate(@RequestBody String requestJson);

    @PostMapping("/account/system_user"+ ControllerActionContract.OPERATE.DELETE)
    // {id:1}
    Response systemUserDelete(@RequestBody String requestJson);
    @PostMapping("/account/gym_admin_user"+ ControllerActionContract.OPERATE.DELETE)
    // {id:1}
    Response gymAdminUserDelete(@RequestBody String requestJson);
    @PostMapping("/account/gym_group_user"+ ControllerActionContract.OPERATE.DELETE)
    // {id:1}
    Response gymGroupUserDelete(@RequestBody String requestJson);

    @PostMapping("/account/gym_group_user"+ ControllerActionContract.OPERATE.GET)
    // {"gym_id":123}
    Response gymAdminUserGetByGymId(@RequestBody String requestJson);

    @PostMapping("/account/gym_admin_user/"+ControllerActionContract.OPERATE.GET)
    // {"id":1}
    // {"name":"who"}
    // {"phone":"13012341234"}
    Response gymAdminUserGetByIdOrNameOrPhone(@RequestBody String requestJson);

    @PostMapping("/account/gym_group_user"+ControllerActionContract.OPERATE.GET)
    // {"id":1}
    // {"name":"who"}
    // {"phone":"13012341234"}
    Response gymGroupUserGetByIdOrNameOrPhone(@RequestBody String requestJson);
}
