package com.crm.mgr.test;

import com.crm.mgr.app.CrmMgrApplication;
import com.crm.mgr.service.impl.LeadStatusService;
import com.crm.mgr.test.tool.AuthTestTool;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
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
public class LeadStatusRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private LeadStatusService leadStatusService;

    @Test
    public void shouldGetLeadStatusById() throws Exception {
        String token = AuthTestTool.obtainAccessToken(mockMvc, "sa", "sa");
        mockMvc.perform(get("/api/v1/lead-status/aa4e766e-9928-44fa-a34b-87e5db77e860")
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("lead")));
    }

    @Test
    public void shouldGetLeadStatuses() throws Exception {
        String token = AuthTestTool.obtainAccessToken(mockMvc, "sa", "sa");
        mockMvc.perform(get("/api/v1/lead-status")
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status", is("lead")));
    }
}
