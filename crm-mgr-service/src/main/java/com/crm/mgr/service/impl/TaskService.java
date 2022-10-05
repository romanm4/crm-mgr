package com.crm.mgr.service.impl;

import com.crm.mgr.dto.TaskDto;
import com.crm.mgr.specification.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    Page<TaskDto> searchTask(List<SearchCriteria> searchCriteria, Pageable pageable);
    TaskDto createTask(TaskDto dto);
    TaskDto deleteTaskById(UUID id);
    TaskDto modifyTask(TaskDto dto, UUID id);
    List<TaskDto> getTasks();
    TaskDto getTaskById(UUID id);
    void assignTaskStatusToTask(UUID taskStatusId, UUID taskId);
    void assignTodoDescToTask(UUID todoDescId, UUID taskId);
    void assignTodoTypeToTask(UUID todoTypeId, UUID taskId);
    void assignUserToTask(UUID userId, UUID taskId);
    void assignLeadToTask(UUID leadId, UUID taskId);
    void deleteUserTask(UUID userId, UUID taskId);
    void deleteLeadTask(UUID leadId, UUID taskId);
}
