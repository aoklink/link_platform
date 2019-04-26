package com.linkfeeling.platform.data.play.member;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.linkfeeling.common.jdbc.support.JdbcSession;
import com.linkfeeling.common.jdbc.support.JdbcSupport;
import com.linkfeeling.common.jdbc.util.JdbcUtil;
import com.linkfeeling.platform.data.play.bean.GymPlayMemberWithCoach;
import com.linkfeeling.platform.data.play.bean.request.GymMemberTrans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class GymPlayMemberWithCoachComponent {

    public static final String TABLE_NAME = "gym_play_member_with_coach";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<GymPlayMemberWithCoach> findAllByCoachUid(String coachUid){
        return JdbcUtil.query(jdbcTemplate,"select * from "+TABLE_NAME+" where coach_uid = ?",new BeanPropertyRowMapper<>(GymPlayMemberWithCoach.class),coachUid);
    }

    public Optional<GymPlayMemberWithCoach> findByStudentUid(String studentUid){
        return JdbcUtil.queryForObject(jdbcTemplate,"select * from "+TABLE_NAME+" where student_uid = ?",new BeanPropertyRowMapper<>(GymPlayMemberWithCoach.class),studentUid);
    }

    public GymPlayMemberWithCoach save(GymPlayMemberWithCoach gymPlayMemberWithCoach,boolean add )throws Exception {
        JdbcSession jdbcSession =
                JdbcSupport.newSession(gymPlayMemberWithCoach);
        gymPlayMemberWithCoach.setBindTime(new Date());
        fillCommon(jdbcSession, gymPlayMemberWithCoach);
        if(add){
            jdbcSession.insertNormal(jdbcTemplate);
        }else{
            jdbcSession.updateWithCustomId(jdbcTemplate,"student_uid");
        }
        return gymPlayMemberWithCoach;
    }

    private void fillCommon(JdbcSession jdbcSession, GymPlayMemberWithCoach gymPlayMemberWithCoach) {
//        private String coachUid;
//        private String studentUid;
//        private String studentNick;
//        private Long gymId;
//        private Date bindTime;
        jdbcSession.field("coach_uid",gymPlayMemberWithCoach.getCoachUid())
                .field("student_uid",gymPlayMemberWithCoach.getStudentUid())
                .field("student_nick",gymPlayMemberWithCoach.getStudentNick())
                .field("gym_id",gymPlayMemberWithCoach.getGymId())
                .field("bind_time",gymPlayMemberWithCoach.getBindTime());
    }


    public void trans(GymMemberTrans gymMemberTrans)throws Exception{
        JSONArray jsonArray = JSONObject.parseArray(gymMemberTrans.getStudentUidArray());
        String toCoachUid = gymMemberTrans.getToCoachUid();
        for (int i = 0; i < jsonArray.size(); i++) {
            String studentUid = jsonArray.getString(i);
            Optional<GymPlayMemberWithCoach> gymPlayMemberWithCoach = findByStudentUid(studentUid);
            if(StringUtils.isEmpty(toCoachUid)){
                gymPlayMemberWithCoach.ifPresent(this::delete);
            }else {
                if (gymPlayMemberWithCoach.isPresent()) {
                    GymPlayMemberWithCoach gymPlayMemberWithCoachValue = gymPlayMemberWithCoach.get();
                    gymPlayMemberWithCoachValue.setBindTime(new Date());
                    gymPlayMemberWithCoachValue.setGymId(gymMemberTrans.getGymId());
                    gymPlayMemberWithCoachValue.setCoachUid(gymMemberTrans.getToCoachUid());
                    save(gymPlayMemberWithCoachValue, false);
                } else {
                    GymPlayMemberWithCoach gymPlayMemberWithCoachValue = new GymPlayMemberWithCoach();
                    gymPlayMemberWithCoachValue.setCoachUid(gymMemberTrans.getToCoachUid());
                    gymPlayMemberWithCoachValue.setGymId(gymMemberTrans.getGymId());
                    gymPlayMemberWithCoachValue.setStudentUid(studentUid);
                    save(gymPlayMemberWithCoachValue, true);
                }
            }
        }
    }

    private void delete(GymPlayMemberWithCoach gymPlayMemberWithCoach){
        jdbcTemplate.execute("delete from "+TABLE_NAME+ " where student_uid = '"+gymPlayMemberWithCoach.getStudentUid()+"'");
    }


}
