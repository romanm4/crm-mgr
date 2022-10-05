package com.crm.mgr.test;

import com.crm.mgr.app.CrmMgrApplication;
import com.crm.mgr.dto.RoleDto;
import com.crm.mgr.service.impl.RoleService;
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
public class RoleRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RoleService roleService;

    @Test
    public void shouldCreateRole() throws Exception {
        RoleDto roleDto = new RoleDto();
        roleDto.setRole("role");
        mockMvc.perform(post("/api/v1/role")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(roleDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.role", is("role")));
    }

    @Test
    public void shouldDeleteRole() throws Exception {
        RoleDto roleDto = new RoleDto();
        roleDto.setRole("role");
        roleDto = roleService.createRole(roleDto);
        mockMvc.perform(delete("/api/v1/role/" + roleDto.getId().toString())
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldModifyRole() throws Exception {
        RoleDto roleDto = new RoleDto();
        roleDto.setRole("role");
        roleDto = roleService.createRole(roleDto);
        mockMvc.perform(put("/api/v1/role/" + roleDto.getId().toString())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(roleDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.role", is("role")));
    }

    @Test
    public void shouldGetRoleById() throws Exception {
        RoleDto roleDto = new RoleDto();
        roleDto.setRole("role");
        roleDto = roleService.createRole(roleDto);
        mockMvc.perform(get("/api/v1/role/" + roleDto.getId().toString())
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.role", is("role")));
    }

    @Test
    public void shouldGetRoles() throws Exception {
        RoleDto roleDto = new RoleDto();
        roleDto.setRole("role");
        roleDto = roleService.createRole(roleDto);
        mockMvc.perform(get("/api/v1/role")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].role", is("Sales Rep")));
    }
}
