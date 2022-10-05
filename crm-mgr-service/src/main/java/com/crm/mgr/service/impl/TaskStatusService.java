package com.crm.mgr.service.impl;

import com.crm.mgr.dto.TaskStatusDto;
import java.util.List;
import java.util.UUID;

public interface TaskStatusService {
    List<TaskStatusDto> getTaskStatuses();
    TaskStatusDto getTaskStatusById(UUID id);
}
