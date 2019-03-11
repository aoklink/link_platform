package com.linkfeeling.account.data.gym.admin;

import com.linkfeeling.account.data.gym.admin.bean.GymAdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class GymAdminUserComponent {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<GymAdminUser> findByName(String name){
        RowMapper<GymAdminUser> rowMapper= new BeanPropertyRowMapper<>(GymAdminUser.class);
        return Optional.of(jdbcTemplate.queryForObject("select * from gym_admin_user where name=?", rowMapper,name));
    }

    public GymAdminUser save(GymAdminUser systemUser){
        //TODO
        if(systemUser.getId()==null){
            return systemUser;
        }else{
            return systemUser;
        }
    }

    public GymAdminUser toResponse(GymAdminUser gymAdminUser){
        gymAdminUser.setPassword("******");
        return gymAdminUser;
    }

    public Optional<GymAdminUser> findById(Long id) {
        RowMapper<GymAdminUser> rowMapper= new BeanPropertyRowMapper<>(GymAdminUser.class);
        return Optional.of(jdbcTemplate.queryForObject("select * from gym_admin_user where id=?", rowMapper,id));
    }

    public Optional<GymAdminUser> findByGymIdAndName(Long gym_id, String name) {
        RowMapper<GymAdminUser> rowMapper= new BeanPropertyRowMapper<>(GymAdminUser.class);
        return Optional.of(jdbcTemplate.queryForObject("select * from gym_admin_user where gym_id=? and name=?", rowMapper,gym_id,name));
    }
}
