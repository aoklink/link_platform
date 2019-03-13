package com.linkfeeling.api.controller.gym.proxy;

import javax.servlet.http.HttpServletRequest;

/**
 * 为请求的Json增加额外参数
 */
public interface APIWrapper {
    String wrap(HttpServletRequest request, String requestJson)throws NoPermissionException;

    class NoPermissionException extends Exception{
        public NoPermissionException(String message) {
            super(message);
        }
    }
}
