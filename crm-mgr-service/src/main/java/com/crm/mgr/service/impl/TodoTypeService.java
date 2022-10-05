package com.crm.mgr.service.impl;

import com.crm.mgr.dto.TodoTypeDto;
import java.util.List;
import java.util.UUID;

public interface TodoTypeService {
    List<TodoTypeDto> getTodoTypes();
    TodoTypeDto getTodoTypeById(UUID id);
}
