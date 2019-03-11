package com.linkfeeling.account.data.system.user;

import com.linkfeeling.account.data.system.user.bean.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SystemUserComponent {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<SystemUser> findByName(String name){
        RowMapper<SystemUser> rowMapper= new BeanPropertyRowMapper<>(SystemUser.class);
        return Optional.of(jdbcTemplate.queryForObject("select * from system_user where name=?", rowMapper,name));
    }

    public SystemUser save(SystemUser systemUser){
        //TODO
        if(systemUser.getId()==null){
            return systemUser;
        }else{
            return systemUser;
        }
    }

    public SystemUser toResponse(SystemUser systemUser){
        systemUser.setPassword("******");
        return systemUser;
    }
}
