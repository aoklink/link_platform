package com.linkfeeling.api.comsumer;

import com.linkfeeling.common.controller.ControllerActionContract;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@Service
@FeignClient(value = "link-account-p-online")
public interface GymAccountServer {

    @PostMapping(value = {"/account/common"+ ControllerActionContract.OPERATE.EXIST},consumes = "application/json")
    // {"phone":"13012341234"}
    String userExist(@RequestBody String requestJson);

    @PostMapping(value = {"/account/common"+ ControllerActionContract.OPERATE.FIND},consumes = "application/json")
    // {"phone":"13012341234"}
    String userFind(@RequestBody String requestJson);

    @PostMapping(value = {"/account/system_user"+ ControllerActionContract.OPERATE.ADD},consumes = "application/json")
    // {name:"system_user","phone":"13012341234","password":"psw"}
    String systemUserAdd(@RequestBody String requestJson);

    @PostMapping(value = {"/account/gym_admin_user"+ ControllerActionContract.OPERATE.ADD},consumes = "application/json")
    // {name:"system_user","phone":"13012341234","password":"psw"}
    String gymAdminUserAdd(@RequestBody String requestJson);

    @PostMapping(value = {"/account/system_user"+ ControllerActionContract.OPERATE.UPDATE},consumes = "application/json")
    // {id:1,name:"system_user","phone":"13012341234","password":"psw"}
    String systemUserUpdate(@RequestBody String requestJson);

    @PostMapping(value = {"/account/gym_admin_user"+ ControllerActionContract.OPERATE.UPDATE},consumes = "application/json")
    // {id:1,name:"system_user","phone":"13012341234","password":"psw"}
    String gymAdminUserUpdate(@RequestBody String requestJson);


    @PostMapping(value = {"/account/system_user"+ ControllerActionContract.OPERATE.DELETE},consumes = "application/json")
    // {id:1}
    String systemUserDelete(@RequestBody String requestJson);
    @PostMapping(value = {"/account/gym_admin_user"+ ControllerActionContract.OPERATE.DELETE},consumes = "application/json")
    // {id:1}
    String gymAdminUserDelete(@RequestBody String requestJson);


    @PostMapping(value = {"/account/gym_admin_user/"+ControllerActionContract.OPERATE.GET_BIND},consumes = "application/json")
        // {"id":1}
        // {"name":"who"}
        // {"phone":"13012341234"}
    String gymAdminUserGetByGymId(@RequestBody String requestJson);

//    @PostMapping("/account/gym_group_user"+ControllerActionContract.OPERATE.GET)
//    // {"id":1}
//    // {"name":"who"}
//    // {"phone":"13012341234"}
//    String gymGroupUserGetByIdOrNameOrPhone(@RequestBody String requestJson);

//    @PostMapping("/account/gym_admin_user"+ ControllerActionContract.OPERATE.UPDATE_GROUP)
//        // {id:1,name:"system_user","phone":"13012341234","password":"psw"}
//    String gymAdminUserUpdateGroup(@RequestBody String requestJson);

//    @PostMapping("/account/gym_group_user"+ ControllerActionContract.OPERATE.DELETE)
//        // {id:1}
//    String gymGroupUserDelete(@RequestBody String requestJson);
    //    @PostMapping("/account/system_user"+ ControllerActionContract.OPERATE.VERIFY)
//    // {"phone":"13012341234","password":"md5(psw)"}
//    String systemUserLogin(@RequestBody String requestJson);

//    @PostMapping("/account/gym_admin_user"+ ControllerActionContract.OPERATE.VERIFY)
//    // {"phone":"13012341234","password":"md5(psw)"}
//    String gymAdminUserLogin(@RequestBody String requestJson);
//
//    @PostMapping("/account/gym_group_user"+ ControllerActionContract.OPERATE.VERIFY)
//    // {"phone":"13012341234","password":"md5(psw)"}
//    String gymGroupUserLogin(@RequestBody String requestJson);
//@PostMapping("/account/gym_group_user"+ ControllerActionContract.OPERATE.ADD)
//// {name:"system_user","phone":"13012341234","password":"psw"}
//String gymGroupUserAdd(@RequestBody String requestJson);
//@PostMapping("/account/gym_group_user"+ ControllerActionContract.OPERATE.UPDATE)
//// {id:1,name:"system_user","phone":"13012341234","password":"psw"}
//String gymGroupUserUpdate(@RequestBody String requestJson);



//    @PostMapping("/account/gym_group_user"+ ControllerActionContract.OPERATE.GET)
//        // {"id":123}
//    String gymGroupUserGetById(@RequestBody String requestJson);
//
//    @PostMapping("/account/gym_admin_user/"+ControllerActionContract.OPERATE.GET)
//        // {"id":1}
//        // {"name":"who"}
//        // {"phone":"13012341234"}
//    String gymAdminUserGetByIdOrNameOrPhone(@RequestBody String requestJson);

}
