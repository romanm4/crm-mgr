package com.crm.mgr.rest;

import com.crm.mgr.dto.TaskStatusDto;
import com.crm.mgr.service.impl.TaskStatusService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/task-status")
public class TaskStatusRestController {
    private final TaskStatusService taskStatusService;

    public TaskStatusRestController(TaskStatusService taskStatusService) {
        this.taskStatusService = taskStatusService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public List<TaskStatusDto> getTaskStatuses() {
        return taskStatusService.getTaskStatuses();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public TaskStatusDto getTaskStatusById(@PathVariable UUID id) {
        return taskStatusService.getTaskStatusById(id);
    }
}
