package com.linkfeeling.platform.data.play.coach;

import com.linkfeeling.common.jdbc.util.JdbcUtil;
import com.linkfeeling.platform.data.play.bean.GymPlayCoach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlayCoachComponent {

    public static final String TABLE_NAME = "coach_account";

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Optional<GymPlayCoach> findByPhoneNum(String phoneNum) {
        return JdbcUtil.queryForObject(jdbcTemplate,"select * from "+TABLE_NAME+" where phone_num = ?", new BeanPropertyRowMapper<>(GymPlayCoach.class),phoneNum);
    }

    public List<GymPlayCoach> findAllByGymId(Long gymId){
        return JdbcUtil.query(jdbcTemplate,"select * from "+TABLE_NAME+" where gym_id = ?", new BeanPropertyRowMapper<>(GymPlayCoach.class),gymId);
    }

    public void delete(Long id)throws Exception{
        jdbcTemplate.execute("delete from "+TABLE_NAME+" where id="+id);
    }

    public Optional<GymPlayCoach> findByUid(String uid) {
        return JdbcUtil.queryForObject(jdbcTemplate,"select * from "+TABLE_NAME+" where uid = ?", new BeanPropertyRowMapper<>(GymPlayCoach.class),uid);
    }
}
