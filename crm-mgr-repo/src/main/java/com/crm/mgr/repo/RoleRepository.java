package com.crm.mgr.repo;

import com.crm.mgr.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Serializable> {
    List<RoleEntity> findAll();
}
