package com.crm.mgr.service.impl;

import com.crm.mgr.dto.UserStatusDto;
import com.crm.mgr.entity.UserStatusEntity;
import com.crm.mgr.mapper.UserStatusMapper;
import com.crm.mgr.repo.UserStatusRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "user-status")
public class UserStatusServiceImpl implements UserStatusService {

    private final UserStatusRepository userStatusRepository;
    private final UserStatusMapper userStatusMapper;

    public UserStatusServiceImpl(UserStatusRepository userStatusRepository, UserStatusMapper userStatusMapper) {
        this.userStatusRepository = userStatusRepository;
        this.userStatusMapper = userStatusMapper;
    }

    @Cacheable
    @Override
    public List<UserStatusDto> getUserStatuses() {
        List<UserStatusEntity> entities = userStatusRepository.findAll();
        return userStatusMapper.entityListToDto(entities);
    }

    @Cacheable
    @Override
    public UserStatusDto getUserStatusById(UUID id) {
        UserStatusEntity entity = userStatusRepository.findById(id).orElseThrow();
        return userStatusMapper.entityToDto(entity);
    }
}
