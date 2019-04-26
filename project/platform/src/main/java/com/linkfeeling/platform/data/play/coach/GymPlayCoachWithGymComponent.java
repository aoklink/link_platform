package com.linkfeeling.platform.data.play.coach;

import com.linkfeeling.common.jdbc.support.JdbcSession;
import com.linkfeeling.common.jdbc.support.JdbcSupport;
import com.linkfeeling.common.jdbc.util.JdbcUtil;
import com.linkfeeling.platform.data.play.bean.GymPlayCoachWithGym;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class GymPlayCoachWithGymComponent {
    public static final String TABLE_NAME = "gym_play_coach_with_gym";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public GymPlayCoachWithGym save(GymPlayCoachWithGym gymPlayCoachWithGym) throws Exception {
        gymPlayCoachWithGym.setBindTime(new Date());
        JdbcSession jdbcSession =
                JdbcSupport.newSession(gymPlayCoachWithGym);
        fillCommon(jdbcSession,gymPlayCoachWithGym);
        jdbcSession.insertNormal(jdbcTemplate);
        return gymPlayCoachWithGym;
    }

    private void fillCommon(JdbcSession jdbcSession, GymPlayCoachWithGym gymPlayCoachWithGym) {
        jdbcSession.field("coach_uid",gymPlayCoachWithGym.getCoachUid())
                .field("gym_id",gymPlayCoachWithGym.getGymId())
                .field("bind_time",gymPlayCoachWithGym.getBindTime());
    }

    public boolean checkBind(GymPlayCoachWithGym gymPlayCoachWithGym){
        return findByCoachUid(gymPlayCoachWithGym.getCoachUid()).isPresent();
    }

    public List<GymPlayCoachWithGym> findAllByGymId(Long gymId){
        return JdbcUtil.query(jdbcTemplate,"select * from "+TABLE_NAME+" where gym_id = ?",new BeanPropertyRowMapper<>(GymPlayCoachWithGym.class),gymId);
    }

    public Optional<GymPlayCoachWithGym> findByCoachUid(String coachUid){
        return JdbcUtil.queryForObject(jdbcTemplate,"select * from "+TABLE_NAME+" where coach_uid = ?",new BeanPropertyRowMapper<>(GymPlayCoachWithGym.class),coachUid);
    }

    public void delete(GymPlayCoachWithGym gymPlayCoachWithGym) throws Exception{
        jdbcTemplate.execute("delete from "+TABLE_NAME+" where coach_uid = '"+gymPlayCoachWithGym.getCoachUid()+"'");
    }
}
