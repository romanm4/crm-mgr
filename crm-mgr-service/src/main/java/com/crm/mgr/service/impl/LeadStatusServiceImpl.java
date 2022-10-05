package com.crm.mgr.service.impl;

import com.crm.mgr.dto.LeadStatusDto;
import com.crm.mgr.entity.LeadStatusEntity;
import com.crm.mgr.mapper.LeadStatusMapper;
import com.crm.mgr.repo.LeadStatusRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "lead-status")
public class LeadStatusServiceImpl implements LeadStatusService {
    private final LeadStatusRepository leadStatusRepository;
    private final LeadStatusMapper leadStatusMapper;

    public LeadStatusServiceImpl(LeadStatusRepository leadStatusRepository, LeadStatusMapper leadStatusMapper) {
        this.leadStatusRepository = leadStatusRepository;
        this.leadStatusMapper = leadStatusMapper;
    }

    @Cacheable
    @Override
    public List<LeadStatusDto> getLeadStatuses() {
        List<LeadStatusEntity> entities = leadStatusRepository.findAll();
        return leadStatusMapper.entityListToDto(entities);
    }

    @Cacheable
    @Override
    public LeadStatusDto getLeadStatusById(UUID id) {
        LeadStatusEntity entity = leadStatusRepository.findById(id).orElseThrow();
        return leadStatusMapper.entityToDto(entity);
    }
}
