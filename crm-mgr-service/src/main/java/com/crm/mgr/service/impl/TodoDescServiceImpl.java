package com.crm.mgr.service.impl;

import com.crm.mgr.dto.TodoDescDto;
import com.crm.mgr.entity.TodoDescEntity;
import com.crm.mgr.mapper.TodoDescMapper;
import com.crm.mgr.repo.TodoDescRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "todo-desc")
public class TodoDescServiceImpl implements TodoDescService {
    private final TodoDescRepository todoDescRepository;
    private final TodoDescMapper todoDescMapper;

    public TodoDescServiceImpl(TodoDescRepository todoDescRepository, TodoDescMapper todoDescMapper) {
        this.todoDescRepository = todoDescRepository;
        this.todoDescMapper = todoDescMapper;
    }

    @Cacheable
    @Override
    public List<TodoDescDto> getTodoDescs() {
        List<TodoDescEntity> entities = todoDescRepository.findAll();
        return todoDescMapper.entityListToDto(entities);
    }

    @Cacheable
    @Override
    public TodoDescDto getTodoDescById(UUID id) {
        TodoDescEntity entity = todoDescRepository.findById(id).orElseThrow();
        return todoDescMapper.entityToDto(entity);
    }
}
