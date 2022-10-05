package com.crm.mgr.service.impl;

import com.crm.mgr.dto.TaskStatusDto;
import com.crm.mgr.entity.TaskStatusEntity;
import com.crm.mgr.mapper.TaskStatusMapper;
import com.crm.mgr.repo.TaskStatusRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "task-status")
public class TaskStatusServiceImpl implements TaskStatusService {
    private final TaskStatusRepository taskStatusRepository;
    private final TaskStatusMapper taskStatusMapper;

    public TaskStatusServiceImpl(TaskStatusRepository taskStatusRepository, TaskStatusMapper taskStatusMapper) {
        this.taskStatusRepository = taskStatusRepository;
        this.taskStatusMapper = taskStatusMapper;
    }

    @Cacheable
    @Override
    public List<TaskStatusDto> getTaskStatuses() {
        List<TaskStatusEntity> entities = taskStatusRepository.findAll();
        return taskStatusMapper.entityListToDto(entities);
    }

    @Cacheable
    @Override
    public TaskStatusDto getTaskStatusById(UUID id) {
        TaskStatusEntity entity = taskStatusRepository.findById(id).orElseThrow();
        return taskStatusMapper.entityToDto(entity);
    }
}
