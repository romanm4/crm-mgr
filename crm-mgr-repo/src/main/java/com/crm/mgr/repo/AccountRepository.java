package com.crm.mgr.repo;

import com.crm.mgr.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Serializable> {
    List<AccountEntity> findAll();
    Optional<AccountEntity> findByLogin(String login);
}
