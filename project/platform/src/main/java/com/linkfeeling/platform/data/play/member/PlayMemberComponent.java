package com.linkfeeling.platform.data.play.member;

import com.linkfeeling.common.jdbc.util.JdbcUtil;
import com.linkfeeling.platform.data.play.bean.GymPlayMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlayMemberComponent {
    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public List<GymPlayMember> listByGymId(Long gymId){
//
//    }

    public List<GymPlayMember> listByGymName(String gymName){
        return JdbcUtil.query(jdbcTemplate,"select * from gym_bind_members where gym_name = ?", new BeanPropertyRowMapper<>(GymPlayMember.class),gymName);
    }

    public Optional<GymPlayMember> findByUid(String uid){
        return JdbcUtil.queryForObject(jdbcTemplate,"select * from gym_bind_members where uid = ?", new BeanPropertyRowMapper<>(GymPlayMember.class),uid);
    }

    public Optional<GymPlayMember> findByPhoneNum(String phoneNum){
        return JdbcUtil.queryForObject(jdbcTemplate,"select * from gym_bind_members where phone_num = ?", new BeanPropertyRowMapper<>(GymPlayMember.class),phoneNum);
    }
}
