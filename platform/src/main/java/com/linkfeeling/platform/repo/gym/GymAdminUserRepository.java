package com.linkfeeling.platform.repo.gym;

import com.linkfeeling.platform.bean.jpa.gym.GymAdminUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GymAdminUserRepository extends CrudRepository<GymAdminUser, Long> {

    Optional<GymAdminUser> findByGymIdAndName(Long gymId, String name);
}
