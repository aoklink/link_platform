package com.linkfeeling.account.data.gym.admin;

import com.linkfeeling.account.data.gym.admin.bean.GymAdminUser;
import com.linkfeeling.common.jdbc.support.JdbcSession;
import com.linkfeeling.common.jdbc.support.JdbcSupport;
import com.linkfeeling.common.jdbc.util.JdbcUtil;
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
        return JdbcUtil.queryForObject(jdbcTemplate,"select * from gym_admin_user where name=?", rowMapper,name);
    }

    public GymAdminUser save(GymAdminUser gymAdminUser)throws Exception{
        JdbcSession jdbcSession =
                JdbcSupport.newSession(gymAdminUser)
                        .field("name",gymAdminUser.getName())
                        .field("password",gymAdminUser.getPassword())
                        .field("phone",gymAdminUser.getPhone())
                        .field("gym_id",gymAdminUser.getGymId());
        if(gymAdminUser.getId()==null){
            Number key = jdbcSession.insert(jdbcTemplate);
            gymAdminUser.setId(key.longValue());
            return gymAdminUser;
        }else{
            jdbcSession.field("id",gymAdminUser.getId());
            jdbcSession.update(jdbcTemplate);
            return gymAdminUser;
        }
    }

    public GymAdminUser toResponse(GymAdminUser gymAdminUser){
        gymAdminUser.setPassword("******");
        return gymAdminUser;
    }

    public Optional<GymAdminUser> findById(Long id) {
        RowMapper<GymAdminUser> rowMapper= new BeanPropertyRowMapper<>(GymAdminUser.class);
        return JdbcUtil.queryForObject(jdbcTemplate,"select * from gym_admin_user where id=?", rowMapper,id);
    }

    public Optional<GymAdminUser> findByGymIdAndName(Long gym_id, String name) {
        RowMapper<GymAdminUser> rowMapper= new BeanPropertyRowMapper<>(GymAdminUser.class);
        return JdbcUtil.queryForObject(jdbcTemplate,"select * from gym_admin_user where gym_id=? and name=?", rowMapper,gym_id,name);
    }

    public Optional<GymAdminUser> findByGymId(Long gym_id){
        RowMapper<GymAdminUser> rowMapper= new BeanPropertyRowMapper<>(GymAdminUser.class);
        return JdbcUtil.queryForObject(jdbcTemplate,"select * from gym_admin_user where gym_id=?", rowMapper,gym_id);
    }

    public Optional<GymAdminUser> findByNameOrPhone(String phone) {
        RowMapper<GymAdminUser> rowMapper= new BeanPropertyRowMapper<>(GymAdminUser.class);
        return JdbcUtil.queryForObject(jdbcTemplate,"select * from gym_admin_user where name=? or phone=?", rowMapper,phone,phone);
    }

    public void delete(GymAdminUser gymAdminUser)throws Exception {
        jdbcTemplate.execute("delete from gym_admin_user where id="+gymAdminUser.getId());
    }
}
