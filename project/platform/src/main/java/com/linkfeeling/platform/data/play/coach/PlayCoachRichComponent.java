package com.linkfeeling.platform.data.play.coach;

import com.linkfeeling.platform.data.play.bean.GymPlayCoach;
import com.linkfeeling.platform.data.play.bean.GymPlayCoachWithGym;
import com.linkfeeling.platform.data.play.bean.GymPlayMember;
import com.linkfeeling.platform.data.play.bean.GymPlayMemberWithCoach;
import com.linkfeeling.platform.data.play.member.GymPlayMemberWithCoachComponent;
import com.linkfeeling.platform.data.play.member.PlayMemberComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PlayCoachRichComponent {

    @Autowired
    private GymPlayCoachWithGymComponent gymPlayCoachWithGymComponent;

    @Autowired
    private PlayCoachComponent playCoachComponent;

    @Autowired
    private GymPlayMemberWithCoachComponent gymPlayMemberWithCoachComponent;

    @Autowired
    private PlayMemberComponent playMemberComponent;

    public List<Map> findAllCoachByGymId(Long gymId){
        List<Map> coachList = new ArrayList<>();
        List<GymPlayCoachWithGym> gymPlayCoachWithGyms =  gymPlayCoachWithGymComponent.findAllByGymId(gymId);
        gymPlayCoachWithGyms.forEach(gymPlayCoachWithGym -> {
            Optional<GymPlayCoach> gymPlayCoachOptional =  playCoachComponent.findByUid(gymPlayCoachWithGym.getCoachUid());
            if(gymPlayCoachOptional.isPresent()){
                List<GymPlayMemberWithCoach> memberWithCoachList = gymPlayMemberWithCoachComponent.findAllByCoachUid(gymPlayCoachOptional.get().getUid());
                coachList.add(buildCoachMap(gymPlayCoachWithGym,gymPlayCoachOptional.get(),memberWithCoachList));
            }
        });
        return coachList;
    }

    private Map<String,Object> buildCoachMap(GymPlayCoachWithGym gymPlayCoachWithGym, GymPlayCoach gymPlayCoach, List<GymPlayMemberWithCoach> memberWithCoachList){
        Map<String,Object> valueMap = new HashMap<>();
        valueMap.put("coach_uid",gymPlayCoachWithGym.getCoachUid());
        valueMap.put("gym_id",gymPlayCoachWithGym.getGymId());
        valueMap.put("bind_time",gymPlayCoachWithGym.getBindTime());

        valueMap.put("phone_num",gymPlayCoach.getPhoneNum());
        valueMap.put("user_name",gymPlayCoach.getPhoneNum());
        valueMap.put("head_icon",gymPlayCoach.getPhoneNum());
        valueMap.put("uid",gymPlayCoach.getPhoneNum());
        valueMap.put("user_type",gymPlayCoach.getPhoneNum());
        valueMap.put("build_time",gymPlayCoach.getPhoneNum());
        valueMap.put("student_count",memberWithCoachList.size());

        return valueMap;
    }

    public List<Map> findAllMemberByCoachUid(String coachUid) {
        List<Map> memberList = new ArrayList<>();
        List<GymPlayMemberWithCoach> memberWithCoachList = gymPlayMemberWithCoachComponent.findAllByCoachUid(coachUid);
        memberWithCoachList.forEach(gymPlayMemberWithCoach -> {
            Optional<GymPlayMember> gymPlayMemberOptional = playMemberComponent.findByUid(gymPlayMemberWithCoach.getStudentUid());
            if(gymPlayMemberOptional.isPresent()){
                memberList.add(buildMemberMap(gymPlayMemberWithCoach,gymPlayMemberOptional.get()));
            }
        });
        return memberList;
    }

    private Map<String,Object> buildMemberMap(GymPlayMemberWithCoach gymPlayMemberWithCoach,GymPlayMember gymPlayMember){
        Map<String,Object> valueMap = new HashMap<>();
        valueMap.put("nick_name",gymPlayMember.getNickName());
        valueMap.put("student_nick",gymPlayMemberWithCoach.getStudentNick());
        valueMap.put("uid",gymPlayMember.getUid());
        valueMap.put("phone_num",gymPlayMember.getPhoneNum());
        valueMap.put("bind_time",gymPlayMemberWithCoach.getBindTime());
        return valueMap;
    }
}
