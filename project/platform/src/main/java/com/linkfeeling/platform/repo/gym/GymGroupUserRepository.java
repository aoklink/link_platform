package com.linkfeeling.platform.repo.gym;

import com.linkfeeling.platform.bean.jpa.gym.GymGroupUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GymGroupUserRepository extends CrudRepository<GymGroupUser,Long> {
    Optional<GymGroupUser> findByName(String name);
}
