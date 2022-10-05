package com.crm.mgr.test;

import com.crm.mgr.app.CrmMgrApplication;
import com.crm.mgr.dto.*;
import com.crm.mgr.service.impl.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {CrmMgrApplication.class})
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.yaml")
public class TaskRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskStatusService taskStatusService;
    @Autowired
    private TodoDescService todoDescService;
    @Autowired
    private TodoTypeService todoTypeService;
    @Autowired
    private UserService userService;
    @Autowired
    private LeadService leadService;

    @Test
    public void shouldCreateTask() throws Exception {
        TaskDto taskDto = new TaskDto();
        taskDto.setIsNewTodo(1);
        mockMvc.perform(post("/api/v1/task")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(taskDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isNewTodo", is(1)));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        TaskDto taskDto = new TaskDto();
        taskDto.setIsNewTodo(1);
        taskDto = taskService.createTask(taskDto);
        mockMvc.perform(delete("/api/v1/task/" + taskDto.getId().toString())
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldModifyTask() throws Exception {
        TaskDto taskDto = new TaskDto();
        taskDto.setIsNewTodo(1);
        taskDto = taskService.createTask(taskDto);
        mockMvc.perform(put("/api/v1/task/" + taskDto.getId().toString())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(taskDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isNewTodo", is(1)));
    }

    @Test
    public void shouldGetTaskById() throws Exception {
        TaskDto taskDto = new TaskDto();
        taskDto.setIsNewTodo(1);
        taskDto = taskService.createTask(taskDto);
        mockMvc.perform(get("/api/v1/task/" + taskDto.getId().toString())
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isNewTodo", is(1)));
    }

    @Test
    public void shouldGetTasks() throws Exception {
        TaskDto taskDto = new TaskDto();
        taskDto.setIsNewTodo(1);
        taskDto = taskService.createTask(taskDto);
        mockMvc.perform(get("/api/v1/task")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isNewTodo", is(1)));
    }

    @Test
    public void shouldAssignTaskStatusToTask() throws Exception {
        TaskDto taskDto = new TaskDto();
        taskDto.setIsNewTodo(1);
        taskDto = taskService.createTask(taskDto);

        List<TaskStatusDto> taskStatuses = taskStatusService.getTaskStatuses();
        mockMvc.perform(put("/api/v1/task/" + taskDto.getId().toString() + "/status/" + taskStatuses.get(0).getId().toString())
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAssignTodoDescToTask() throws Exception {
        TaskDto taskDto = new TaskDto();
        taskDto.setIsNewTodo(1);
        taskDto = taskService.createTask(taskDto);

        List<TodoDescDto> todoDescs = todoDescService.getTodoDescs();
        mockMvc.perform(put("/api/v1/task/" + taskDto.getId().toString() + "/todo-desc/" + todoDescs.get(0).getId().toString())
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAssignTodoTypeToTask() throws Exception {
        TaskDto taskDto = new TaskDto();
        taskDto.setIsNewTodo(1);
        taskDto = taskService.createTask(taskDto);

        List<TodoTypeDto> todoTypes = todoTypeService.getTodoTypes();
        mockMvc.perform(put("/api/v1/task/" + taskDto.getId().toString() + "/todo-type/" + todoTypes.get(0).getId().toString())
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAssignUserToTask() throws Exception {
        TaskDto taskDto = new TaskDto();
        taskDto.setIsNewTodo(1);
        taskDto = taskService.createTask(taskDto);

        UserDto userDto = new UserDto();
        userDto.setEmail("bob@gmail.com");
        userDto = userService.createUser(userDto);

        mockMvc.perform(put("/api/v1/task/" + taskDto.getId().toString() + "/user/" + userDto.getId().toString())
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAssignLeadToTask() throws Exception {
        TaskDto taskDto = new TaskDto();
        taskDto.setIsNewTodo(1);
        taskDto = taskService.createTask(taskDto);

        LeadDto leadDto = new LeadDto();
        leadDto.setEmail("bob@gmail.com");
        leadDto = leadService.createLead(leadDto);

        mockMvc.perform(put("/api/v1/task/" + taskDto.getId().toString() + "/lead/" + leadDto.getId().toString())
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteUserTask() throws Exception {
        TaskDto taskDto = new TaskDto();
        taskDto.setIsNewTodo(1);
        taskDto = taskService.createTask(taskDto);

        UserDto userDto = new UserDto();
        userDto.setEmail("bob@gmail.com");
        userDto = userService.createUser(userDto);

        taskService.assignUserToTask(userDto.getId(), taskDto.getId());
        mockMvc.perform(delete("/api/v1/task/" + taskDto.getId().toString() + "/user/" + userDto.getId().toString())
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteLeadTask() throws Exception {
        TaskDto taskDto = new TaskDto();
        taskDto.setIsNewTodo(1);
        taskDto = taskService.createTask(taskDto);

        LeadDto leadDto = new LeadDto();
        leadDto.setEmail("bob@gmail.com");
        leadDto = leadService.createLead(leadDto);

        taskService.assignLeadToTask(leadDto.getId(), taskDto.getId());
        mockMvc.perform(delete("/api/v1/task/" + taskDto.getId().toString() + "/lead/" + leadDto.getId().toString())
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}
