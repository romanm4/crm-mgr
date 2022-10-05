package com.crm.mgr.rest;

import com.crm.mgr.dto.TodoTypeDto;
import com.crm.mgr.service.impl.TodoTypeService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/task/todo-type")
public class TodoTypeRestController {
    private final TodoTypeService todoTypeService;

    public TodoTypeRestController(TodoTypeService todoTypeService) {
        this.todoTypeService = todoTypeService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public List<TodoTypeDto> getTodoTypes() {
        return todoTypeService.getTodoTypes();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public TodoTypeDto getTodoTypeById(@PathVariable UUID id) {
        return todoTypeService.getTodoTypeById(id);
    }
}
