package com.crm.mgr.rest;

import com.crm.mgr.dto.RoleDto;
import com.crm.mgr.service.impl.RoleService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/role")
public class RoleRestController {
    private final RoleService roleService;

    public RoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Admin')")
    public RoleDto createRole(@Valid @RequestBody RoleDto dto) {
        return roleService.createRole(dto);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public RoleDto deleteRoleById(@PathVariable UUID id) {
        return roleService.deleteRoleById(id);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Admin')")
    public RoleDto modifyRole(@Valid @RequestBody RoleDto dto, @PathVariable UUID id) {
        return roleService.modifyRole(dto, id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public List<RoleDto> getRoles() {
        return roleService.getRoles();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public RoleDto getRoleById(@PathVariable UUID id) {
        return roleService.getRoleById(id);
    }
}
