package com.linkfeeling.common.jdbc.util;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class JdbcUtil {
    public static <T> Optional<T> queryForObject(JdbcTemplate jdbcTemplate,String sql, RowMapper<T> rowMapper, @Nullable Object... args) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql,rowMapper,args));
        }catch (EmptyResultDataAccessException empty){
            return Optional.empty();
        }
    }
}
