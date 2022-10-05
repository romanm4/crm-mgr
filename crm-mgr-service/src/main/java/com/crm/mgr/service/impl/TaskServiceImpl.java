package com.crm.mgr.service.impl;

import com.crm.mgr.app.rabbitmq.producer.TaskMessageProducer;
import com.crm.mgr.dto.LeadDto;
import com.crm.mgr.dto.TaskDto;
import com.crm.mgr.entity.*;
import com.crm.mgr.mapper.TaskMapper;
import com.crm.mgr.repo.*;
import com.crm.mgr.specification.SearchCriteria;
import com.crm.mgr.specification.TaskSpecification;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "task")
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskStatusRepository taskStatusRepository;
    private final TodoDescRepository todoDescRepository;
    private final TodoTypeRepository todoTypeRepository;
    private final UserRepository userRepository;
    private final LeadRepository leadRepository;
    private final TaskMapper taskMapper;
    private final TaskMessageProducer taskMessageProducer;

    public TaskServiceImpl(TaskRepository taskRepository, TaskStatusRepository taskStatusRepository, TodoDescRepository todoDescRepository, TodoTypeRepository todoTypeRepository, UserRepository userRepository, LeadRepository leadRepository, TaskMapper taskMapper, TaskMessageProducer taskMessageProducer) {
        this.taskRepository = taskRepository;
        this.taskStatusRepository = taskStatusRepository;
        this.todoDescRepository = todoDescRepository;
        this.todoTypeRepository = todoTypeRepository;
        this.userRepository = userRepository;
        this.leadRepository = leadRepository;
        this.taskMapper = taskMapper;
        this.taskMessageProducer = taskMessageProducer;
    }

    @Override
    public Page<TaskDto> searchTask(List<SearchCriteria> searchCriteria, Pageable pageable) {
        TaskSpecification taskSpecification = new TaskSpecification();
        searchCriteria.forEach(taskSpecification::add);
        Page<TaskEntity> taskEntities = taskRepository.findAll(taskSpecification, pageable);
        List<TaskDto> dtos = taskMapper.entityListToDto(taskEntities.getContent());
        return new PageImpl<>(dtos, pageable, taskEntities.getTotalElements());
    }

    @Override
    public TaskDto createTask(TaskDto dto) {
        TaskEntity entity = taskRepository.save(taskMapper.dtoToEntity(dto));
        TaskDto taskDto = taskMapper.entityToDto(entity);
        taskMessageProducer.sendTaskMessage(taskDto);
        return taskDto;
    }

    @Override
    public TaskDto deleteTaskById(UUID id) {
        TaskEntity entity = taskRepository.findById(id).orElseThrow();
        return taskMapper.entityToDto(entity);
    }

    @Override
    public TaskDto modifyTask(TaskDto dto, UUID id) {
        TaskEntity entity = taskRepository.findById(id).orElseThrow();
        entity.setIsNewTodo(dto.getIsNewTodo());
        entity.setDateOfInitialTask(dto.getDateOfInitialTask());
        entity.setTodoDueDate(dto.getTodoDueDate());
        entity = taskRepository.save(entity);
        return taskMapper.entityToDto(entity);
    }

    @Cacheable
    @Override
    public List<TaskDto> getTasks() {
        List<TaskEntity> entities = taskRepository.findAll();
        return taskMapper.entityListToDto(entities);
    }

    @Cacheable
    @Override
    public TaskDto getTaskById(UUID id) {
        TaskEntity entity = taskRepository.findById(id).orElseThrow();
        return taskMapper.entityToDto(entity);
    }

    @Override
    public void assignTaskStatusToTask(UUID taskStatusId, UUID taskId) {
        TaskStatusEntity taskStatus = taskStatusRepository.findById(taskStatusId).orElseThrow();
        TaskEntity task = taskRepository.findById(taskId).orElseThrow();
        task.setTaskStatus(taskStatus);
        taskRepository.save(task);
    }

    @Override
    public void assignTodoDescToTask(UUID todoDescId, UUID taskId) {
        TodoDescEntity todoDesc = todoDescRepository.findById(todoDescId).orElseThrow();
        TaskEntity task = taskRepository.findById(taskId).orElseThrow();
        task.setTodoDesc(todoDesc);
        taskRepository.save(task);
    }

    @Override
    public void assignTodoTypeToTask(UUID todoTypeId, UUID taskId) {
        TodoTypeEntity todoType = todoTypeRepository.findById(todoTypeId).orElseThrow();
        TaskEntity task = taskRepository.findById(taskId).orElseThrow();
        task.setTodoType(todoType);
        taskRepository.save(task);
    }

    @Override
    public void assignUserToTask(UUID userId, UUID taskId) {
        TaskEntity task = taskRepository.findById(taskId).orElseThrow();
        UserEntity user = userRepository.findById(userId).orElseThrow();
        task.setUser(user);
        taskRepository.save(task);
    }

    @Override
    public void assignLeadToTask(UUID leadId, UUID taskId) {
        TaskEntity task = taskRepository.findById(taskId).orElseThrow();
        LeadEntity lead = leadRepository.findById(leadId).orElseThrow();
        task.setLead(lead);
        taskRepository.save(task);
    }

    @Override
    public void deleteUserTask(UUID userId, UUID taskId) {
        TaskEntity task = taskRepository.findById(taskId).orElseThrow();
        task.setUser(null);
        taskRepository.save(task);
    }

    @Override
    public void deleteLeadTask(UUID leadId, UUID taskId) {
        TaskEntity task = taskRepository.findById(taskId).orElseThrow();
        task.setLead(null);
        taskRepository.save(task);
    }
}
