package com.crm.mgr.repo;

import com.crm.mgr.entity.LeadEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.List;

@Repository
public interface LeadRepository extends CrudRepository<LeadEntity, Serializable>, JpaSpecificationExecutor<LeadEntity> {
    List<LeadEntity> findAll();
}
