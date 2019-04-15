package com.linkfeeling.account.data.account.bean;


import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 用户帐号信息
 */
public class UserAccount implements Serializable {
    private static final long serialVersionUID = 1061401;

    public static final String DAO_FIELD_PHONE_NUM = "phone_num";
    public static final String DAO_FIELD_USER_NAME = "user_name";
    public static final String DAO_FIELD_PASSWORD = "password";
    public static final String DAO_FIELD_UID = "uid";
    public static final String DAO_FIELD_WX_OPENID = "wx_openid";
    public static final String DAO_FIELD_QQ = "qq";
    public static final String DAO_FIELD_USER_TYPE = "user_type";
    public static final String DAO_FIELD_PLATFORM = "platform";
    public static final String DAO_FIELD_AGE = "age";
    public static final String DAO_FIELD_GENDER = "gender";
    public static final String DAO_FIELD_STATURE = "stature";
    public static final String DAO_FIELD_WEIGHT = "weight";
    public static final String DAO_FIELD_HEAD_ICON = "head_icon";
    public static final String DAO_FIELD_SITUATION = "situation";
    public static final String DAO_FIELD_GOAL = "goal";
    public static final String DAO_FIELD_BUILD_TIME = "build_time";
    public static final String DAO_FIELD_GYM_NAMES = "gym_names";

    //用户名
    private String userName;
    //头像
    private String headIcon;
    //电话号码
    private String phoneNum;
    //登陆密码
    private String pwd;
    //用户唯一识别码，跟phoneNum对应
    private String uid;
    //微信id
    private String wxId;
    //QQ id
    private String qqId;
    //user type
    private String userType;
    //platform
    private String platform;
    //age
    private String age;

    //gender
    private String gender;

    //stature 身高
    private String stature;

    //体重
    private String weight;

    //当前运动状态
    private String situation;

    //运动目的
    private String goal;


    //build time
    private String buildTime;
    //用户的健身房
    private String gymNames;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setPhoneNum(String num) {
        this.phoneNum = num;
    }

    public String getPhoneNum() {
        return this.phoneNum;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwd() {
        return pwd;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }


    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public String getWxId() {
        return wxId;
    }

    public void setQQId(String qqId) {
        this.qqId = qqId;
    }

    public String getQQId() {
        return qqId;
    }


    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPlatform() {
        return platform;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getGender() {
        return gender;
    }


    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeight() {
        return weight;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getGoal() {
        return goal;
    }


    public void setSituation(String situation) {
        this.situation = situation;
    }


    public String getSituation() {
        return situation;
    }


    public void setStature(String stature) {
        this.stature = stature;
    }

    public String getStature() {
        return stature;
    }

    public String getGymNames() {
        return gymNames;
    }

    public void setGymNames(String gymNames) {
        this.gymNames = gymNames;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof UserAccount) {
            if (this.phoneNum != null
                    && this.phoneNum.equals(((UserAccount) obj).phoneNum)) {
                return true;
            } else {
                return false;
            }
        } else {
            return super.equals(obj);
        }
    }

    @Override
    public int hashCode() {
        if (phoneNum != null) {
            return phoneNum.hashCode();
        } else {
            return super.hashCode();
        }
    }


    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put(DAO_FIELD_USER_NAME, userName);
        object.put(DAO_FIELD_HEAD_ICON, headIcon);
        object.put(DAO_FIELD_PHONE_NUM, phoneNum);
        object.put(DAO_FIELD_PASSWORD, pwd);
        object.put(DAO_FIELD_UID, uid);
        object.put(DAO_FIELD_WX_OPENID, wxId);
        object.put(DAO_FIELD_QQ, qqId);
        object.put(DAO_FIELD_USER_TYPE, userType);
        object.put(DAO_FIELD_PLATFORM, platform);
        object.put(DAO_FIELD_AGE, age);
        object.put(DAO_FIELD_GENDER, gender);
        object.put(DAO_FIELD_STATURE, stature);
        object.put(DAO_FIELD_WEIGHT, weight);
        object.put(DAO_FIELD_SITUATION, situation);
        object.put(DAO_FIELD_GOAL, goal);
        object.put(DAO_FIELD_HEAD_ICON, headIcon);
        object.put(DAO_FIELD_GYM_NAMES, gymNames);
        return object.toString();
    }
}
