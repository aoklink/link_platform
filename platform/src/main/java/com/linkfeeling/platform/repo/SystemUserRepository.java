package com.linkfeeling.platform.repo;

import com.linkfeeling.platform.bean.SystemUser;
import org.springframework.data.repository.CrudRepository;

public interface SystemUserRepository extends CrudRepository<SystemUser, String> {
}
