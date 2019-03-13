package com.linkfeeling.api.controller.gym.proxy;

import com.linkfeeling.common.interactive.response.Response;
import org.springframework.web.bind.annotation.RequestBody;

public interface APIProxy {
    Response proxy(String requestJson);
}
