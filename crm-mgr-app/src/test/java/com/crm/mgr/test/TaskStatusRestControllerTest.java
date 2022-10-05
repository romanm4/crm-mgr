package com.crm.mgr.test;

import com.crm.mgr.app.CrmMgrApplication;
import com.crm.mgr.service.impl.TaskStatusService;
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
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrmMgrApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.yaml")
public class TaskStatusRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TaskStatusService taskStatusService;

    @Test
    public void shouldGetTaskStatusById() throws Exception {

        mockMvc.perform(get("/api/v1/task-status/cfa4178d-3356-4472-9a8e-aff61f7a7b08")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("pending")));
    }

    @Test
    public void shouldGetTaskStatuses() throws Exception {
        mockMvc.perform(get("/api/v1/task-status")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status", is("pending")));
    }
}
