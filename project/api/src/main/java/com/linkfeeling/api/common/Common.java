package com.linkfeeling.api.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Common {

    private final static String hostUrl = "https://api.weixin.qq.com/sns/jscode2session";

//    /**
//     * 生成uid
//     */
//    public static String generateUid(String phoneNum, String userType) {
//        return MD5Util.getMD5(phoneNum + ":link:" + userType);
//    }


    /**
     * 手机号码校验
     */
    public static boolean isPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147)|(199))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * HttpServletRequest 转化成json
     */
    public static JSONObject receivePost(HttpServletRequest request) throws Exception {
        // 读取请求内容
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        //将json字符串转换为json对象
        JSONObject json = JSON.parseObject(sb.toString());
        return json;
    }

}
