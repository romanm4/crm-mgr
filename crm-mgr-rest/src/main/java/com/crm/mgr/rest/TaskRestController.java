package com.crm.mgr.rest;

import com.crm.mgr.dto.LeadDto;
import com.crm.mgr.dto.TaskDto;
import com.crm.mgr.service.impl.TaskService;
import com.crm.mgr.specification.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/task")
public class TaskRestController {
    private final TaskService taskService;

    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(path = "/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public Page<TaskDto> searchTask(@RequestBody List<SearchCriteria> searchCriteria, @RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return taskService.searchTask(searchCriteria, pageable);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public TaskDto createTask(@Valid @RequestBody TaskDto dto) {
        return taskService.createTask(dto);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public TaskDto deleteTaskById(@PathVariable UUID id) {
        return taskService.deleteTaskById(id);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public TaskDto modifyTask(@Valid @RequestBody TaskDto dto, @PathVariable UUID id) {
        return taskService.modifyTask(dto, id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public List<TaskDto> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public TaskDto getTaskById(@PathVariable UUID id) {
        return taskService.getTaskById(id);
    }

    @PutMapping(path = "/{taskId}/status/{taskStatusId}")
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public void assignTaskStatusToTask(@PathVariable UUID taskStatusId, @PathVariable UUID taskId) {
        taskService.assignTaskStatusToTask(taskStatusId, taskId);
    }

    @PutMapping(path = "/{taskId}/todo-desc/{todoDescId}")
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public void assignTodoDescToTask(@PathVariable UUID todoDescId, @PathVariable UUID taskId) {
        taskService.assignTodoDescToTask(todoDescId, taskId);
    }

    @PutMapping(path = "/{taskId}/todo-type/{todoTypeId}")
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public void assignTodoTypeToTask(@PathVariable UUID todoTypeId, @PathVariable UUID taskId) {
        taskService.assignTodoTypeToTask(todoTypeId, taskId);
    }

    @PutMapping(path = "/{taskId}/user/{userId}")
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public void assignUserToTask(@PathVariable UUID userId, @PathVariable UUID taskId) {
        taskService.assignUserToTask(userId, taskId);
    }

    @PutMapping(path = "/{taskId}/lead/{leadId}")
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public void assignLeadToTask(@PathVariable UUID leadId, @PathVariable UUID taskId) {
        taskService.assignLeadToTask(leadId, taskId);
    }

    @DeleteMapping(path = "/{taskId}/user/{userId}")
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    void deleteUserTask(@PathVariable UUID userId, @PathVariable UUID taskId) {
        taskService.deleteUserTask(userId, taskId);
    }

    @DeleteMapping(path = "/{taskId}/lead/{leadId}")
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    void deleteLeadTask(@PathVariable UUID leadId, @PathVariable UUID taskId) {
        taskService.deleteLeadTask(leadId, taskId);
    }
}
