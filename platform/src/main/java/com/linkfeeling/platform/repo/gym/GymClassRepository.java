package com.linkfeeling.platform.repo.gym;

import com.linkfeeling.platform.bean.jpa.gym.GymClass;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GymClassRepository extends CrudRepository<GymClass,Long> {
    void deleteByGymId(Long gymId);
    List<GymClass> getAllByGymId(Long gymId);
}
