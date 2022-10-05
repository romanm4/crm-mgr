package com.crm.mgr.repo;

import com.crm.mgr.entity.LeadStatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.List;

@Repository
public interface LeadStatusRepository extends CrudRepository<LeadStatusEntity, Serializable> {
    List<LeadStatusEntity> findAll();
}
