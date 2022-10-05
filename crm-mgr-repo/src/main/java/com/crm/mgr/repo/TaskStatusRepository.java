package com.crm.mgr.repo;

import com.crm.mgr.entity.TaskStatusEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.List;

@Repository
public interface TaskStatusRepository extends CrudRepository<TaskStatusEntity, Serializable> {
    List<TaskStatusEntity> findAll();
}
