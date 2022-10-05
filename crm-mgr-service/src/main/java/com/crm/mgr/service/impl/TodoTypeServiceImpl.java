package com.crm.mgr.service.impl;

import com.crm.mgr.dto.TodoTypeDto;
import com.crm.mgr.entity.TodoTypeEntity;
import com.crm.mgr.mapper.TodoTypeMapper;
import com.crm.mgr.repo.TodoTypeRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "todo-type")
public class TodoTypeServiceImpl implements TodoTypeService {
    private final TodoTypeRepository todoTypeRepository;
    private final TodoTypeMapper todoTypeMapper;

    public TodoTypeServiceImpl(TodoTypeRepository todoTypeRepository, TodoTypeMapper todoTypeMapper) {
        this.todoTypeRepository = todoTypeRepository;
        this.todoTypeMapper = todoTypeMapper;
    }

    @Cacheable
    @Override
    public List<TodoTypeDto> getTodoTypes() {
        List<TodoTypeEntity> todoTypes = todoTypeRepository.findAll();
        return todoTypeMapper.entityListToDto(todoTypes);
    }

    @Cacheable
    @Override
    public TodoTypeDto getTodoTypeById(UUID id) {
        TodoTypeEntity todoType = todoTypeRepository.findById(id).orElseThrow();
        return todoTypeMapper.entityToDto(todoType);
    }
}
