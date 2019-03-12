package com.linkfeeling.platform.data.gym.coach;

import com.linkfeeling.common.jdbc.support.JdbcSession;
import com.linkfeeling.common.jdbc.support.JdbcSupport;
import com.linkfeeling.common.jdbc.util.JdbcUtil;
import com.linkfeeling.platform.data.gym.bean.GymCoach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class GymCoachComponent {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public GymCoach save(GymCoach gymCoach)throws Exception {
        JdbcSession jdbcSession =
                JdbcSupport.newSession(gymCoach);
        fillCommon(jdbcSession,gymCoach);
        if(gymCoach.getId()==null){
            Number key = jdbcSession.insert(jdbcTemplate);
            gymCoach.setId(key.longValue());
            return gymCoach;
        }else{
            jdbcSession.field("id",gymCoach.getId());
            jdbcSession.update(jdbcTemplate);
            return findById(gymCoach.getId()).get();
        }
    }

    public GymCoach toSimpleResponse(GymCoach gymCoach){
        return gymCoach;
    }

    private void fillCommon(JdbcSession jdbcSession,GymCoach gymCoach){
        jdbcSession
                .field("name",gymCoach.getName())
                .field("label",gymCoach.getLabel())
                .field("gym_id",gymCoach.getGymId());
    }

    public Optional<GymCoach> findById(Long id) {
        return JdbcUtil.queryForObject(jdbcTemplate,"select * from gym_coach where id = ?", new BeanPropertyRowMapper<>(GymCoach.class),id);
    }

    public void delete(GymCoach gymCoach) {
        jdbcTemplate.execute("delete from gym_coach where id="+gymCoach.getId());
    }
}
