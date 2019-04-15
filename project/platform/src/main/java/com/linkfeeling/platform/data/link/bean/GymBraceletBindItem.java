package com.linkfeeling.platform.data.link.bean;

import com.alibaba.fastjson.JSONObject;

/**
 * 绑定手环相关信息
 */
public class GymBraceletBindItem {
    public static final String ID = "id";

    public static final String GYM_NAME = "gym_name";
    //手环id
    public static final String BRACELET_ID = "bracelet_id";
    //手环状态
    public static final String STATUS = "status";
    //是否已读 1表示未读,0表示已读
    public static final String IS_READ = "is_read";
    //会员名称
    public static final String USER_NAME = "user_name";
    //会员电话
    public static final String PHONE_NUM = "phone_num";

    //会员uid
    public static final String UID = "uid";

    //绑定时间
    public static final String BIND_TIME = "bind_time";
    //绑定时间戳
    public static final String BIND_TIMESTAMP = "bind_timestamp";
    //解绑时间
    public static final String UNBIND_TIME = "unbind_time";
    //卡路里
    public static final String CALORIE = "calorie";

    private String bracelet_id;
    private boolean status;
    private String user_name;
    private String phone_num;
    private String uid;
    private Long bind_time;
    private String bind_date;
    private Long unbind_time;
    private String unbind_date;
    private String calorie;


    public void setBraceletId(String id) {
        this.bracelet_id = id;
    }

    public String getBraceletId() {
        return this.bracelet_id;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setUserName(String userName) {
        this.user_name = userName;
    }

    public String getUserName() {
        return user_name;
    }

    public void setPhoneNum(String phoneNum) {
        this.phone_num = phoneNum;
    }

    public String getPhoneNum() {
        return phone_num;
    }

    public void setBindTime(Long bindTime) {
        this.bind_time = bindTime;
    }

    public long getBindTime() {
        return bind_time;
    }

    public void setBindDate(String bindDate) {
        this.bind_date = bindDate;
    }

    public String getBindDate() {
        return bind_date;
    }

    public void setUnbindTime(Long unbindTime) {
        this.unbind_time = unbindTime;
    }

    public long getUnbindTime() {
        return unbind_time;
    }

    public void setUnbindDate(String unbindDate) {
        this.unbind_date = unbindDate;
    }

    public String getUnbindDate() {
        return unbind_date;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public JSONObject toJSON() {
        JSONObject object = new JSONObject();
        object.put(BRACELET_ID, this.bracelet_id);
        object.put(STATUS, this.status);
        object.put(USER_NAME, this.user_name);
        object.put(PHONE_NUM, this.phone_num);
        object.put(UID, uid);
        object.put(BIND_TIME, this.bind_time);
        object.put(UNBIND_TIME, this.unbind_time);
        object.put(CALORIE, this.calorie);
        return object;
    }

    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put(BRACELET_ID, this.bracelet_id);
        object.put(STATUS, this.status);
        object.put(USER_NAME, this.user_name);
        object.put(PHONE_NUM, this.phone_num);
        object.put(UID, uid);
        object.put(BIND_TIME, this.bind_time);
        object.put(UNBIND_TIME, this.unbind_time);
        object.put(CALORIE, this.calorie);
        return object.toString();
    }
}
