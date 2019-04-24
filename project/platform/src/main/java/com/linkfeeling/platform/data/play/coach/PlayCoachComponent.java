package com.linkfeeling.platform.data.play.coach;

import com.linkfeeling.common.jdbc.support.JdbcSession;
import com.linkfeeling.common.jdbc.support.JdbcSupport;
import com.linkfeeling.common.jdbc.util.JdbcUtil;
import com.linkfeeling.platform.data.play.bean.GymPlayCoach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class PlayCoachComponent {

    public static final String TABLE_NAME = "gym_play_coach";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public GymPlayCoach save(GymPlayCoach gymPlayCoach)throws Exception{
        JdbcSession jdbcSession =
                JdbcSupport.newSession(gymPlayCoach);
        if(gymPlayCoach.getId()==null){
            if(gymPlayCoach.getJoinTime()==null){
                gymPlayCoach.setJoinTime(new Date());
            }
            fillCommon(jdbcSession,gymPlayCoach);
            Number key = jdbcSession.insert(jdbcTemplate);
            gymPlayCoach.setId(key.longValue());
            return gymPlayCoach;
        }else{
            fillCommon(jdbcSession,gymPlayCoach);
            jdbcSession.field("id", gymPlayCoach.getId());
            jdbcSession.update(jdbcTemplate);
            return findById(gymPlayCoach.getId()).get();
        }
    }

    private void fillCommon(JdbcSession jdbcSession, GymPlayCoach gymCoach){
//        private Long id;
//        private Long gymId;
//        private String name;
//        private String phone;
//        private Date joinTime;
//        private String memberArray;
        jdbcSession
                .field("gym_id",gymCoach.getGymId())
                .field("name",gymCoach.getName())
                .field("phone",gymCoach.getPhone())
                .field("join_time",gymCoach.getJoinTime())
                .field("member_array",gymCoach.getMemberArray());
    }

    public Optional<GymPlayCoach> findById(Long id) {
        return JdbcUtil.queryForObject(jdbcTemplate,"select * from "+TABLE_NAME+" where id = ?", new BeanPropertyRowMapper<>(GymPlayCoach.class),id);
    }

    public List<GymPlayCoach> findAllByGymId(Long gymId){
        return JdbcUtil.query(jdbcTemplate,"select * from "+TABLE_NAME+" where gym_id = ?", new BeanPropertyRowMapper<>(GymPlayCoach.class),gymId);
    }

    public void delete(Long id)throws Exception{
        jdbcTemplate.execute("delete from "+TABLE_NAME+" where id="+id);
    }
}
