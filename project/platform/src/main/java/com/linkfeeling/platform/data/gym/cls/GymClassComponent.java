package com.linkfeeling.platform.data.gym.cls;

import com.linkfeeling.common.jdbc.support.JdbcSession;
import com.linkfeeling.common.jdbc.support.JdbcSupport;
import com.linkfeeling.common.jdbc.util.JdbcUtil;
import com.linkfeeling.platform.data.gym.bean.GymClass;
import com.linkfeeling.platform.data.gym.bean.GymCoach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class GymClassComponent {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public GymClass save(GymClass gymClass)throws Exception {
        JdbcSession jdbcSession =
                JdbcSupport.newSession(gymClass);
        if(gymClass.getState()==null){
            gymClass.setState(0);
        }
        fillCommon(jdbcSession,gymClass);
        if(gymClass.getId()==null){
            Number key = jdbcSession.insert(jdbcTemplate);
            gymClass.setId(key.longValue());
            return gymClass;
        }else{
            jdbcSession.field("id",gymClass.getId());
            jdbcSession.update(jdbcTemplate);
            return findById(gymClass.getId()).get();
        }
    }

    public GymClass toSimpleResponse(GymClass gymClass){
        gymClass.setContent(null);
        return gymClass;
    }

    private void fillCommon(JdbcSession jdbcSession,GymClass gymClass){
        jdbcSession
        .field("title",gymClass.getTitle())
        .field("price_info",gymClass.getPriceInfo())
        .field("content",gymClass.getContent())
        .field("gym_id",gymClass.getGymId())
        .field("state",gymClass.getState());
    }

    public Optional<GymClass> findById(Long id) {
        return JdbcUtil.queryForObject(jdbcTemplate,"select * from gym_class where id = ?", new BeanPropertyRowMapper<>(GymClass.class),id);
    }

    public List<GymClass> findAllByGymId(Long gym_id){
        try {
            return jdbcTemplate.query("select * from gym_class where gym_id = ?", new BeanPropertyRowMapper<>(GymClass.class),gym_id);
        }catch (Exception e){
            return Collections.emptyList();
        }
    }

    public void delete(GymClass gymClass) {
        jdbcTemplate.execute("delete from gym_class where id="+gymClass.getId());
    }

    public List<GymClass> toResponseList(List<GymClass> gymClasses) {
        gymClasses.forEach(gymClass -> gymClass.setContent(null));
        return gymClasses;
    }
}
