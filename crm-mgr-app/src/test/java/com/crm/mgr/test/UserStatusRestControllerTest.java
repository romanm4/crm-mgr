package com.crm.mgr.test;

import com.crm.mgr.app.CrmMgrApplication;
import com.crm.mgr.service.impl.UserStatusService;
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
public class UserStatusRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserStatusService userStatusService;

    @Test
    public void shouldGetUserStatusById() throws Exception {

        mockMvc.perform(get("/api/v1/user-status/f62947f6-2331-4386-aa43-797d2b878859")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("1")));
    }

    @Test
    public void shouldGetUserStatuses() throws Exception {
        mockMvc.perform(get("/api/v1/user-status")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status", is("1")));
    }
}
