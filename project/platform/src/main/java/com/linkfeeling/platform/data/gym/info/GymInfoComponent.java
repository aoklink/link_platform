package com.linkfeeling.platform.data.gym.info;

import com.linkfeeling.common.jdbc.support.JdbcSession;
import com.linkfeeling.common.jdbc.support.JdbcSupport;
import com.linkfeeling.common.jdbc.util.JdbcUtil;
import com.linkfeeling.platform.data.gym.bean.GymInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class GymInfoComponent {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public GymInfo save(GymInfo gymInfo)throws Exception {
        JdbcSession jdbcSession =
                JdbcSupport.newSession(gymInfo);
        fillCommon(jdbcSession,gymInfo);
        if(gymInfo.getId()==null){
            Number key = jdbcSession.insert(jdbcTemplate);
            gymInfo.setId(key.longValue());
            return gymInfo;
        }else{
            jdbcSession.field("id",gymInfo.getId());
            jdbcSession.update(jdbcTemplate);
            return findById(gymInfo.getId()).get();
        }
    }

    public GymInfo toSimpleResponse(GymInfo gymInfo){
        gymInfo.setDisplayImgUrls(null);
        return gymInfo;
    }

    private void fillCommon(JdbcSession jdbcSession,GymInfo gymInfo){
        jdbcSession
        .field("member_count",gymInfo.getMemberCount())
        .field("name",gymInfo.getName())
        .field("city",gymInfo.getCity())
        .field("address",gymInfo.getAddress())
        .field("label",gymInfo.getLabel())
        .field("phone",gymInfo.getPhone())
        .field("logo_url",gymInfo.getLogoUrl())
        .field("display_img_urls",gymInfo.getDisplayImgUrls())
        .field("mini_program_code_url",gymInfo.getMiniProgramCodeUrl());
    }

    public Optional<GymInfo> findById(Long id) {
        return JdbcUtil.queryForObject(jdbcTemplate,"select * from gym_info where id = ?", new BeanPropertyRowMapper<>(GymInfo.class),id);
    }

    public void delete(GymInfo gymInfo) {
        jdbcTemplate.execute("delete from gym_info where id="+gymInfo.getId());
    }
}
