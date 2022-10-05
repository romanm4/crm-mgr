package com.crm.mgr.repo;

import com.crm.mgr.entity.UserStatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.List;

@Repository
public interface UserStatusRepository extends CrudRepository<UserStatusEntity, Serializable> {
    List<UserStatusEntity> findAll();
}
