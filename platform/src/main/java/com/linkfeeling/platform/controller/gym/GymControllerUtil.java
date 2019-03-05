package com.linkfeeling.platform.controller.gym;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.linkfeeling.platform.bean.jpa.gym.GymAdminUser;
import com.linkfeeling.platform.bean.jpa.gym.GymGroupUser;
import com.linkfeeling.platform.bean.jpa.gym.GymInfo;
import com.linkfeeling.platform.interactive.response.Response;
import com.linkfeeling.platform.interactive.response.ResponseDesc;
import com.linkfeeling.platform.interactive.util.ResponseUtil;
import com.linkfeeling.platform.repo.gym.GymAdminUserRepository;
import com.linkfeeling.platform.repo.gym.GymGroupUserRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class GymControllerUtil {
    public static Response onMeRequest(HttpServletRequest servletRequest, GymAdminUserRepository gymAdminUserRepository, GymIDWorker gymIDWorker) {
        String userName = servletRequest.getUserPrincipal().getName();
        Optional<GymAdminUser> adminUser = gymAdminUserRepository.findByName(userName);
        if(adminUser.isPresent()){
           return gymIDWorker.handle(adminUser.get().getGymId());
        }else{
            return ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST);
        }
    }

    public static Response onGroupRequest(HttpServletRequest servletRequest, GymGroupUserRepository gymGroupUserRepository, GymIDListWorker gymIDWorker) {
        String userName = servletRequest.getUserPrincipal().getName();
        Optional<GymGroupUser> gymGroupUser = gymGroupUserRepository.findByName(userName);
        if(gymGroupUser.isPresent()){
            String gymIdList = gymGroupUser.get().getGymIdArray();
            return gymIDWorker.handle(parseGymIdList(gymIdList));
        }else{
            return ResponseUtil.newResponseWithDesc(ResponseDesc.NOT_EXIST);
        }
    }

    private static List<Long> parseGymIdList(String gymIdListString){
        try {
            JSONParser jsonParser = new JSONParser(gymIdListString);
            ArrayList<Object> values = jsonParser.parseArray();
            List<Long> gymIdList = new ArrayList<>();
            for(Object object : values){
                if(object instanceof String){
                    gymIdList.add(Long.parseLong((String) object));
                }else if(object instanceof Integer){
                    gymIdList.add(Long.valueOf((Integer) object));
                }else if(object instanceof Long){
                    gymIdList.add((Long) object);
                }
            }
            return gymIdList;
        }catch (Exception e){
            return Collections.emptyList();
        }
    }

    public interface GymIDWorker{
        Response handle(Long gymId);
    }

    public interface GymIDListWorker{
        Response handle(List<Long> gymIdList);
    }
}
