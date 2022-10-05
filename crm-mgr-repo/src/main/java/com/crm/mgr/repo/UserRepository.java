package com.crm.mgr.repo;

import com.crm.mgr.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Serializable> {
    List<UserEntity> findAll();
    Optional<UserEntity> findByEmail(String email);
}
