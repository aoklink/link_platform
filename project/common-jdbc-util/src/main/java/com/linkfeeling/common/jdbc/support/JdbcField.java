package com.linkfeeling.common.jdbc.support;

import org.springframework.lang.NonNull;

import java.sql.Types;

public class JdbcField {
    String name;
    Object value;

    public JdbcField(String name, @NonNull Object value) {
        this.name = name;
        this.value = value;
    }
}
