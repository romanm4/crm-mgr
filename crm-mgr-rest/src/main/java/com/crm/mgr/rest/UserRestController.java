package com.crm.mgr.rest;

import com.crm.mgr.dto.UserDto;
import com.crm.mgr.service.impl.UserService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Admin')")
    public UserDto createUser(@Valid @RequestBody UserDto dto) {
        return userService.createUser(dto);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public UserDto deleteUserById(@PathVariable UUID id) {
        return userService.deleteUserById(id);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public UserDto modifyUser(@Valid @RequestBody UserDto dto, @PathVariable UUID id) {
        return userService.modifyUser(dto, id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public UserDto getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @PutMapping(path = "/{userId}/role/{roleId}")
    @PreAuthorize("hasAuthority('Admin')")
    public void assignRoleToUser(@PathVariable UUID roleId, @PathVariable UUID userId) {
        userService.assignRoleToUser(roleId, userId);
    }

    @PutMapping(path = "/{userId}/status/{statusId}")
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public void assignUserStatusToUser(@PathVariable UUID statusId, @PathVariable UUID userId) {
        userService.assignUserStatusToUser(statusId, userId);
    }
}
