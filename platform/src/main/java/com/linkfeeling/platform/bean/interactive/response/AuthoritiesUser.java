package com.linkfeeling.platform.bean.interactive.response;

import java.util.List;

public class AuthoritiesUser {
    private Object user;
    private List<String> authorities;

    public AuthoritiesUser(Object user, List<String> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    public Object getUser() {
        return user;
    }

    public List<String> getAuthorities() {
        return authorities;
    }
}
