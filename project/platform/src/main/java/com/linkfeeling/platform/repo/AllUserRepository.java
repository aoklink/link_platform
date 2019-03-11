package com.linkfeeling.platform.repo;

import com.linkfeeling.platform.bean.jpa.SystemUser;
import com.linkfeeling.platform.bean.jpa.gym.GymAdminUser;
import com.linkfeeling.platform.bean.jpa.gym.GymGroupUser;
import com.linkfeeling.platform.repo.gym.GymAdminUserRepository;
import com.linkfeeling.platform.repo.gym.GymGroupUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AllUserRepository {
    @Autowired
    private SystemUserRepository systemUserRepository;

    @Autowired
    private GymAdminUserRepository gymAdminUserRepository;

    @Autowired
    private GymGroupUserRepository gymGroupUserRepository;


    public Optional<Object> findByName(String name){
        Optional<SystemUser> systemUser =  systemUserRepository.findByName(name);
        if(systemUser.isPresent()){
            return Optional.of(systemUser.get());
        }
        Optional<GymAdminUser> gymAdminUser = gymAdminUserRepository.findByName(name);
        if(gymAdminUser.isPresent()){
            return Optional.of(gymAdminUser.get());
        }
        Optional<GymGroupUser> gymGroupUser = gymGroupUserRepository.findByName(name);
        if(gymGroupUser.isPresent()){
            return Optional.of(gymGroupUser.get());
        }
        return Optional.empty();
    }
}
