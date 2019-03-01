package com.linkfeeling.platform.interactive.util;

import com.linkfeeling.platform.interactive.response.Response;

public class ResponseUtil {
    public static <T> Response<T> newSuccess(T data) {
        return new Response<>(200,"OK",data);
    }

    public static Response<Exception> newException(Exception data) {
        return new Response<>(500,data.getMessage(),data);
    }
}
