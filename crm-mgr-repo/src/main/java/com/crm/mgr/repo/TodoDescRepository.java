package com.crm.mgr.repo;

import com.crm.mgr.entity.TodoDescEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.List;

@Repository
public interface TodoDescRepository extends CrudRepository<TodoDescEntity, Serializable> {
    List<TodoDescEntity> findAll();
}
