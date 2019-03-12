package com.linkfeeling.account.data.system.user;

import com.linkfeeling.account.data.system.user.bean.SystemUser;
import com.linkfeeling.common.jdbc.support.JdbcSession;
import com.linkfeeling.common.jdbc.support.JdbcSupport;
import com.linkfeeling.common.jdbc.util.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class SystemUserComponent {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<SystemUser> findByName(String name){
        RowMapper<SystemUser> rowMapper= new BeanPropertyRowMapper<>(SystemUser.class);
        return JdbcUtil.queryForObject(jdbcTemplate,"select * from system_user where name=?", rowMapper,name);
    }

    public Optional<SystemUser> findByNameOrPhone(String name){
        RowMapper<SystemUser> rowMapper= new BeanPropertyRowMapper<>(SystemUser.class);
        return JdbcUtil.queryForObject(jdbcTemplate,"select * from system_user where name=? or phone=?", rowMapper,name,name);
    }

    public SystemUser save(SystemUser systemUser)throws Exception{
        JdbcSession jdbcSession =
                JdbcSupport.newSession(systemUser)
                .field("name",systemUser.getName())
                .field("password",systemUser.getPassword())
                .field("phone",systemUser.getPhone());
        if(systemUser.getId()==null){
            Number key = jdbcSession  .insert(jdbcTemplate);
            systemUser.setId(key.longValue());
            return systemUser;
        }else{
            jdbcSession.field("id",systemUser.getId());
            jdbcSession.update(jdbcTemplate);
            return systemUser;
        }
    }

    public SystemUser toResponse(SystemUser systemUser){
        systemUser.setPassword("******");
        return systemUser;
    }

    public Optional<SystemUser> findById(Long id) {
        RowMapper<SystemUser> rowMapper= new BeanPropertyRowMapper<>(SystemUser.class);
        return JdbcUtil.queryForObject(jdbcTemplate,"select * from system_user where id=?", rowMapper,id);
    }

    public void delete(SystemUser systemUser)throws Exception {
        jdbcTemplate.execute("delete from system_user where id="+systemUser.getId());
    }
}
