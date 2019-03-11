package com.linkfeeling.account.data.gym.group;

import com.linkfeeling.account.data.gym.group.bean.GymGroupUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class GymGroupUserComponent {
    public GymGroupUser save(GymGroupUser gymGroupUser) {
        return null;
    }

    public GymGroupUser toResponse(GymGroupUser gymGroupUserResult) {
        gymGroupUserResult.setPassword("******");
        return gymGroupUserResult;
    }

    public Optional<GymGroupUser> findByName(String name) {
        return null;
    }

    public Optional<GymGroupUser> findById(Long id) {
        return null;
    }
}
