package com.linkfeeling.platform.auth.user.manager;

import com.linkfeeling.platform.bean.jpa.SystemUser;
import com.linkfeeling.platform.bean.jpa.gym.GymAdminUser;
import com.linkfeeling.platform.bean.jpa.gym.GymGroupUser;
import com.linkfeeling.platform.interactive.util.InteractiveBeanUtil;
import com.linkfeeling.platform.repo.AllUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.util.DigestUtils;

import java.util.Collections;
import java.util.Optional;

public class AllUserManager implements UserDetailsManager {
    @Autowired
    private AllUserRepository allUserRepository;
    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return allUserRepository.findByName(username).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Object> objectOptional = allUserRepository.findByName(username);
        if(objectOptional.isPresent()){
            Object object = objectOptional.get();
            if(object instanceof SystemUser){
                SystemUser user = (SystemUser) object;
                return new User(user.getName(), DigestUtils.md5DigestAsHex(user.getPassword().getBytes()), Collections.singleton(new SimpleGrantedAuthority(IUserRole.SYSTEM)));
            }else if (object instanceof GymAdminUser){
                GymAdminUser user = (GymAdminUser) object;
                return new User(user.getName(),DigestUtils.md5DigestAsHex(user.getPassword().getBytes()), Collections.singleton(new SimpleGrantedAuthority(IUserRole.GYM_ADMIN)));
            }else if(object instanceof GymGroupUser){
                GymGroupUser user = (GymGroupUser) object;
                return new User(user.getName(),DigestUtils.md5DigestAsHex(user.getPassword().getBytes()), Collections.singleton(new SimpleGrantedAuthority(IUserRole.GYM_GROUP)));
            }else {
                throw new UsernameNotFoundException(username);
            }
        }else{
            throw new UsernameNotFoundException(username);
        }
    }

    public Object getUserToResponse(String username){
        Optional<Object> objectOptional = allUserRepository.findByName(username);
        if(objectOptional.isPresent()){
            Object object = objectOptional.get();
            if(object instanceof SystemUser){
                SystemUser user = (SystemUser) object;
                return InteractiveBeanUtil.from(user);
            }else if (object instanceof GymAdminUser){
                GymAdminUser user = (GymAdminUser) object;
                return InteractiveBeanUtil.from(user);
            }else if(object instanceof GymGroupUser){
                GymGroupUser user = (GymGroupUser) object;
                return InteractiveBeanUtil.from(user);
            }else {
                throw new UsernameNotFoundException(username);
            }
        }else{
            throw new UsernameNotFoundException(username);
        }
    }
}
