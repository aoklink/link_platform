package com.linkfeeling.platform.controller.system;

import com.linkfeeling.platform.bean.jpa.SystemUser;
import com.linkfeeling.platform.interactive.response.Response;
import com.linkfeeling.platform.interactive.response.ResponseDesc;
import com.linkfeeling.platform.interactive.util.InteractiveBeanUtil;
import com.linkfeeling.platform.interactive.util.ResponseUtil;
import com.linkfeeling.platform.repo.AllUserRepository;
import com.linkfeeling.platform.repo.SimpleJDBCRepository;
import com.linkfeeling.platform.repo.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.util.DigestUtils.md5DigestAsHex;

@RestController
@RequestMapping("/system")
public class SystemController {
    @Autowired
    private SystemUserRepository systemUserRepository;

    @Autowired
    private AllUserRepository allUserRepository;

    @Autowired
    private SimpleJDBCRepository simpleJDBCRepository;

    @RequestMapping("/add_system_user")
    public Response addUser(HttpServletRequest servletRequest, String name, String password){
        try {
            if("platform".equals(servletRequest.getParameter("api_admin"))){
                if(allUserRepository.findByName(name).isPresent()){
                    return ResponseUtil.newResponseWithDesc(ResponseDesc.ALREADY_EXIST,"username already exist.[name=]"+name);
                }
                SystemUser user = systemUserRepository.save(new SystemUser(name, md5DigestAsHex(password.getBytes())));
                return ResponseUtil.newSuccess(InteractiveBeanUtil.from(user));
            }else{
                return ResponseUtil.newResponseWithDesc(ResponseDesc.INVALID_REQUEST);
            }

        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.newException(e);
        }
    }
    @RequestMapping("/test_jdbc")
    public Response testJdbc(){
        simpleJDBCRepository.testConnect();
        return ResponseUtil.newSuccess("Success");
    }

    @RequestMapping("/list_system_user")
    public Response listSystemUser(){
        return ResponseUtil.newSuccess(simpleJDBCRepository.getSystemUserList());
    }
}
