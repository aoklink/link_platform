package com.linkfeeling.platform.repo.gym;

import com.linkfeeling.platform.bean.jpa.gym.GymCoach;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GymCoachRepository extends CrudRepository<GymCoach,Long>{
    void deleteByGymId(Long gymId);

    List<GymCoach> findAllByGymId(Long gymId);

    Optional<GymCoach> findByGymIdAndId(Long gymId,Long id);
}
