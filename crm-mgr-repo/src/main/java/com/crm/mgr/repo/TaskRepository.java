package com.crm.mgr.repo;

import com.crm.mgr.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<TaskEntity, Serializable>, JpaSpecificationExecutor<TaskEntity> {
    List<TaskEntity> findAll();
}
