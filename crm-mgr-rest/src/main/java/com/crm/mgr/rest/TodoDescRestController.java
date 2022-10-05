package com.crm.mgr.rest;

import com.crm.mgr.dto.TodoDescDto;
import com.crm.mgr.service.impl.TodoDescService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/task/todo-desc")
public class TodoDescRestController {
    private final TodoDescService todoDescService;

    public TodoDescRestController(TodoDescService todoDescService) {
        this.todoDescService = todoDescService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public List<TodoDescDto> getTodoDescs() {
        return todoDescService.getTodoDescs();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public TodoDescDto getTodoDescById(@PathVariable UUID id) {
        return todoDescService.getTodoDescById(id);
    }
}
