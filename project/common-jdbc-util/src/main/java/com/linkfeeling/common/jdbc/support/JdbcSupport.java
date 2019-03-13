package com.linkfeeling.common.jdbc.support;

public class JdbcSupport {

    public static <BEAN> JdbcSession<BEAN>  newSession(BEAN bean){
        return new JdbcSession<BEAN> (bean);
    }
}
