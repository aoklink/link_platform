package com.linkfeeling.common.jdbc.support;

import org.springframework.lang.NonNull;

import java.sql.Types;

public class JdbcField {
    String name;
    Object value;
    /**
     * {@link Types}
     */
    int type;

    public JdbcField(String name, @NonNull Object value, int type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public JdbcField(String name, Object value) {
        this.name = name;
        this.value = value;
        if(value instanceof Integer){
            type = Types.BIGINT;
        }else if(value instanceof Long){
            type = Types.BIGINT;
        }else if(value instanceof String){
            type = Types.LONGVARCHAR;
        }
    }
}
