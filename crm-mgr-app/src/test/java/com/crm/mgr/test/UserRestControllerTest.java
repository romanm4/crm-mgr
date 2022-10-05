package com.crm.mgr.test;

import com.crm.mgr.dto.*;
import com.crm.mgr.service.impl.RoleService;
import com.crm.mgr.service.impl.UserService;
import com.crm.mgr.service.impl.UserStatusService;
import org.junit.Test;
import com.crm.mgr.app.CrmMgrApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrmMgrApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.yaml")
public class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserStatusService userStatusService;

    @Test
    public void shouldCreateUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setEmail("bob@gmail.com");
        mockMvc.perform(post("/api/v1/user")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("bob@gmail.com")));
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setEmail("bob@gmail.com");
        userDto = userService.createUser(userDto);
        mockMvc.perform(delete("/api/v1/user/" + userDto.getId().toString())
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldModifyUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setEmail("bob@gmail.com");
        userDto = userService.createUser(userDto);
        mockMvc.perform(put("/api/v1/user/" + userDto.getId().toString())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("bob@gmail.com")));
    }

    @Test
    public void shouldGetUserById() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setEmail("bob@gmail.com");
        userDto = userService.createUser(userDto);
        mockMvc.perform(get("/api/v1/user/" + userDto.getId().toString())
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("bob@gmail.com")));
    }

    @Test
    public void shouldGetUsers() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setEmail("bob@gmail.com");
        userDto = userService.createUser(userDto);
        mockMvc.perform(get("/api/v1/user")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email", is("bob@gmail.com")));
    }

    @Test
    public void shouldAssignRoleToUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setEmail("bob@gmail.com");
        userDto = userService.createUser(userDto);

        mockMvc.perform(put("/api/v1/user/" + userDto.getId().toString() + "/role/043968a0-a9b9-41ea-9e1b-94c74b5f261f")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAssignUserStatusToUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setEmail("bob@gmail.com");
        userDto = userService.createUser(userDto);

        mockMvc.perform(put("/api/v1/user/" + userDto.getId().toString() + "/status/12495ace-8e01-4468-b831-7544f26c458a")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}
