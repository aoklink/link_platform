package com.linkfeeling.platform.data.link.bean;

import com.alibaba.fastjson.JSONObject;

public class MemberItem {
    public final static String ID = "id";
    public final static String NICK_NAME = "nick_name";
    public final static String UID = "uid";
    public final static String PHONE_NUM = "phone_num";
    public final static String GYM_NAME = "gym_name";
    public final static String BIND_TIME = "bind_time";

    private String id;
    private String nickName;
    private String uid;
    private String phoneNum;
    private String gymName;
    private String bindTime;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public String getGymName() {
        return gymName;
    }

    public void setBindTime(String bindTime) {
        this.bindTime = bindTime;
    }

    public String getBindTime() {
        return bindTime;
    }


    public JSONObject toJSONObject(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(MemberItem.ID,id);
        jsonObject.put(MemberItem.UID,uid);
        jsonObject.put(MemberItem.PHONE_NUM,phoneNum);
        jsonObject.put(MemberItem.GYM_NAME,gymName);
        jsonObject.put(MemberItem.NICK_NAME,nickName);
        jsonObject.put(MemberItem.BIND_TIME,bindTime);
        return jsonObject;
    }
}
