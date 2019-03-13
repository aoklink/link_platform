package com.linkfeeling.common.interactive.util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linkfeeling.common.interactive.response.Response;
import com.linkfeeling.common.interactive.response.ResponseDesc;

public class ResponseUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static synchronized Response newSuccess(Object data) {
//        String value = "";
//        try {
//            value = objectMapper.writeValueAsString(data);
//        } catch (JsonProcessingException e) {
//            value = data.toString();
//        }
        return new Response(ResponseDesc.OK.getCode(),ResponseDesc.OK.getMessage(), data);
    }

    public static synchronized Response newException(Exception data) {
        data.printStackTrace();
        return new Response(500,data.getMessage(),"");
    }

    @SuppressWarnings("unchecked")
    public static synchronized Response newResponseWithDesc(ResponseDesc responseDesc) {
        return new Response(responseDesc.getCode(),responseDesc.getMessage(),"");
    }

    @SuppressWarnings("unchecked")
    public static synchronized Response newResponseWithDesc(ResponseDesc responseDesc,String message) {
        return new Response(responseDesc.getCode(),message,"");
    }

    public static Response parse(String responseJson) {
        return JSONObject.parseObject(responseJson,Response.class);
    }

    public static String parse(Response response) {
        return JSONObject.toJSONString(response);
    }

    public static String getDataString(Response response) {
        return String.valueOf(response.getData());
    }
}
