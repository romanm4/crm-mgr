package com.crm.mgr.service.impl;

import com.crm.mgr.dto.TodoDescDto;
import java.util.List;
import java.util.UUID;

public interface TodoDescService {
    List<TodoDescDto> getTodoDescs();
    TodoDescDto getTodoDescById(UUID id);
}
