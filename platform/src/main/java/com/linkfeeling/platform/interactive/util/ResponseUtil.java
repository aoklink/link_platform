package com.linkfeeling.platform.interactive.util;

import com.linkfeeling.platform.interactive.response.Response;
import com.linkfeeling.platform.interactive.response.ResponseDesc;

public class ResponseUtil {
    public static <T> Response<T> newSuccess(T data) {
        return new Response<>(ResponseDesc.OK.getCode(),ResponseDesc.OK.getMessage(),data);
    }

    public static Response<Exception> newException(Exception data) {
        return new Response<>(500,data.getMessage(),data);
    }

    @SuppressWarnings("unchecked")
    public static Response newResponseWithDesc(ResponseDesc responseDesc) {
        return new Response(responseDesc.getCode(),ResponseDesc.OK.getMessage(),"");
    }

    @SuppressWarnings("unchecked")
    public static Response newResponseWithDesc(ResponseDesc responseDesc,String message) {
        return new Response(responseDesc.getCode(),message,"");
    }
}
