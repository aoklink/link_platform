package com.linkfeeling.account.data.common.user.bean;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CommonUserWrapper {
    private Object user;
    private String beanClassName;

    public CommonUserWrapper(Object user, String beanClassName) {
        this.user = user;
        this.beanClassName = beanClassName;
    }

    public CommonUserWrapper() {
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }
}
