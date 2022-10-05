package com.crm.mgr.repo;

import com.crm.mgr.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;

public interface AddressRepository extends CrudRepository<AddressEntity, Serializable> {
    List<AddressEntity> findAll();
}
