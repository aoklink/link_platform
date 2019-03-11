package com.linkfeeling.platform.repo;

import com.linkfeeling.platform.bean.jpa.SystemUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SystemUserRepository extends CrudRepository<SystemUser, Long> {

    Optional<SystemUser> findByName(String name);
}
