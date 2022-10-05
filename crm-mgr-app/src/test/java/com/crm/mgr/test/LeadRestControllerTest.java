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
@SpringBootTest(classes = CrmMgrApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.yaml")
public class LeadRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private LeadService leadService;
    @Autowired
    private LeadStatusService leadStatusService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;

    @Test
    public void shouldCreateLead() throws Exception {
        LeadDto leadDto = new LeadDto();
        leadDto.setEmail("bob@gmail.com");
        mockMvc.perform(post("/api/v1/lead")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(leadDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("bob@gmail.com")));
    }

    @Test
    public void shouldDeleteLead() throws Exception {
        LeadDto leadDto = new LeadDto();
        leadDto.setEmail("bob@gmail.com");
        leadDto = leadService.createLead(leadDto);
        mockMvc.perform(delete("/api/v1/lead/" + leadDto.getId().toString())
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }


    @Test
    public void shouldModifyLead() throws Exception {
        LeadDto leadDto = new LeadDto();
        leadDto.setEmail("bob@gmail.com");
        leadDto = leadService.createLead(leadDto);
        mockMvc.perform(put("/api/v1/lead/" + leadDto.getId().toString())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(leadDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("bob@gmail.com")));
    }

    @Test
    public void shouldGetLeadById() throws Exception {
        LeadDto leadDto = new LeadDto();
        leadDto.setEmail("bob@gmail.com");
        leadDto = leadService.createLead(leadDto);
        mockMvc.perform(get("/api/v1/lead/" + leadDto.getId().toString())
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("bob@gmail.com")));
    }

    @Test
    public void shouldGetLeads() throws Exception {
        LeadDto leadDto = new LeadDto();
        leadDto.setEmail("bob@gmail.com");
        leadDto = leadService.createLead(leadDto);
        mockMvc.perform(get("/api/v1/lead")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email", is("bob@gmail.com")));
    }

    @Test
    public void shouldAssignLeadStatusToLead() throws Exception {
        LeadDto leadDto = new LeadDto();
        leadDto.setEmail("bob@gmail.com");
        leadDto = leadService.createLead(leadDto);

        List<LeadStatusDto> leadStatuses = leadStatusService.getLeadStatuses();
        mockMvc.perform(put("/api/v1/lead/" + leadDto.getId().toString() + "/status/" + leadStatuses.get(0).getId().toString())
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteLeadStatusLead() throws Exception {
        LeadDto leadDto = new LeadDto();
        leadDto.setEmail("bob@gmail.com");
        leadDto = leadService.createLead(leadDto);

        List<LeadStatusDto> leadStatuses = leadStatusService.getLeadStatuses();
        leadService.assignLeadStatusToLead(leadStatuses.get(0).getId(), leadDto.getId());
        mockMvc.perform(delete("/api/v1/lead/" + leadDto.getId().toString() + "/status/" + leadStatuses.get(0).getId().toString())
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAssignTaskToLead() throws Exception {
        LeadDto leadDto = new LeadDto();
        leadDto.setEmail("bob@gmail.com");
        leadDto = leadService.createLead(leadDto);

        TaskDto taskDto = new TaskDto();
        taskDto.setIsNewTodo(1);
        taskDto = taskService.createTask(taskDto);

        mockMvc.perform(put("/api/v1/lead/" + leadDto.getId().toString() + "/task/" + taskDto.getId().toString())
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteTaskLead() throws Exception {
        LeadDto leadDto = new LeadDto();
        leadDto.setEmail("bob@gmail.com");
        leadDto = leadService.createLead(leadDto);

        TaskDto taskDto = new TaskDto();
        taskDto.setIsNewTodo(1);
        taskDto = taskService.createTask(taskDto);
        leadService.assignTaskToLead(leadDto.getId(), taskDto.getId());
        mockMvc.perform(delete("/api/v1/lead/" + leadDto.getId().toString() + "/task/" + taskDto.getId().toString())
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAssignAddressToLead() throws Exception {
        LeadDto leadDto = new LeadDto();
        leadDto.setEmail("bob@gmail.com");
        leadDto = leadService.createLead(leadDto);

        AddressDto addressDto = new AddressDto();
        addressDto.setCity("Warsaw");
        addressDto = addressService.createAddress(addressDto);

        mockMvc.perform(put("/api/v1/lead/" + leadDto.getId().toString() + "/address/" + addressDto.getId().toString())
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteAddressLead() throws Exception {
        LeadDto leadDto = new LeadDto();
        leadDto.setEmail("bob@gmail.com");
        leadDto = leadService.createLead(leadDto);

        AddressDto addressDto = new AddressDto();
        addressDto.setCity("Warsaw");
        addressDto = addressService.createAddress(addressDto);
        leadService.assignAddressToLead(leadDto.getId(), addressDto.getId());
        mockMvc.perform(delete("/api/v1/lead/" + leadDto.getId().toString() + "/address/" + addressDto.getId().toString())
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAssignUserToLead() throws Exception {
        LeadDto leadDto = new LeadDto();
        leadDto.setEmail("bob@gmail.com");
        leadDto = leadService.createLead(leadDto);

        UserDto userDto = new UserDto();
        userDto.setEmail("bob@gmail.com");
        userDto = userService.createUser(userDto);

        mockMvc.perform(put("/api/v1/lead/" + leadDto.getId().toString() + "/user/" + userDto.getId().toString())
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteUserLead() throws Exception {
        LeadDto leadDto = new LeadDto();
        leadDto.setEmail("bob@gmail.com");
        leadDto = leadService.createLead(leadDto);

        UserDto userDto = new UserDto();
        userDto.setEmail("bob@gmail.com");
        userDto = userService.createUser(userDto);
        leadService.assignUserToLead(leadDto.getId(), userDto.getId());
        mockMvc.perform(delete("/api/v1/lead/" + leadDto.getId().toString() + "/user/" + userDto.getId().toString())
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}
