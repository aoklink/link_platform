package com.linkfeeling.platform.comsumer;

import com.linkfeeling.common.controller.ControllerActionContract;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient(value = "link-account-p-online")
public interface AccountServer {

    // 获取账号信息
    @PostMapping(value = {"/account" + ControllerActionContract.OPERATE.GET_ACCOUNT}, consumes = "application/json")
    String get(@RequestBody String toJSONString);
}
