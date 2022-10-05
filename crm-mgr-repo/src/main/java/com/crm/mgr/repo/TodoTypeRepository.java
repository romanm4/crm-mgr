package com.crm.mgr.repo;

import com.crm.mgr.entity.TodoTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.List;

@Repository
public interface TodoTypeRepository extends CrudRepository<TodoTypeEntity, Serializable> {
    List<TodoTypeEntity> findAll();
}
