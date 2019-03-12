package com.linkfeeling.account.data.gym.group;

import com.linkfeeling.account.data.gym.group.bean.GymGroupUser;
import com.linkfeeling.common.jdbc.support.JdbcSession;
import com.linkfeeling.common.jdbc.support.JdbcSupport;
import com.linkfeeling.common.jdbc.util.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Optional;

@Repository
public class GymGroupUserComponent {

    private static final String TABLE_NAME = "gym_group_user";

    private static final String SELECT_HEAD = "select * from "+TABLE_NAME+" where ";

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public GymGroupUser save(GymGroupUser gymGroupUser)throws Exception {
        JdbcSession jdbcSession =
                JdbcSupport.newSession(gymGroupUser)
                        .field("name",gymGroupUser.getName())
                        .field("password",gymGroupUser.getPassword())
                        .field("phone",gymGroupUser.getPhone())
                        .field("gym_id_array",gymGroupUser.getGymIdArray());
        if(gymGroupUser.getId()==null){
            Number key = jdbcSession.insert(jdbcTemplate);
            gymGroupUser.setId(key.longValue());
            return gymGroupUser;
        }else{
            jdbcSession.field("id",gymGroupUser.getId());
            jdbcSession.update(jdbcTemplate);
            return gymGroupUser;
        }
    }

    public GymGroupUser toResponse(GymGroupUser gymGroupUserResult) {
        gymGroupUserResult.setPassword("******");
        return gymGroupUserResult;
    }

    public Optional<GymGroupUser> findByName(String name) {
        return query("name=?",name);
    }

    public Optional<GymGroupUser> findById(Long id) {
        return query("id=?",id);
    }

    public Optional<GymGroupUser> findByNameOrPhone(String phone) {
        return query("name=? or phone=?",phone,phone);
    }

    private Optional<GymGroupUser> query(String condition,Object ... args){
        RowMapper<GymGroupUser> rowMapper= new BeanPropertyRowMapper<>(GymGroupUser.class);
        return JdbcUtil.queryForObject(jdbcTemplate,SELECT_HEAD+condition, rowMapper,args);
    }
}
