package com.crm.mgr.rest;

import com.crm.mgr.dto.UserStatusDto;
import com.crm.mgr.service.impl.UserStatusService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user-status")
public class UserStatusRestController {

    private final UserStatusService userStatusService;

    public UserStatusRestController(UserStatusService userStatusService) {
        this.userStatusService = userStatusService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public List<UserStatusDto> getUserStatuses() {
        return userStatusService.getUserStatuses();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public UserStatusDto getUserStatusById(@PathVariable UUID id) {
        return userStatusService.getUserStatusById(id);
    }
}
