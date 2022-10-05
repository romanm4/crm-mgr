package com.crm.mgr.service.impl;

import com.crm.mgr.dto.RoleDto;
import java.util.List;
import java.util.UUID;

public interface RoleService {
    RoleDto createRole(RoleDto dto);
    RoleDto deleteRoleById(UUID id);
    RoleDto modifyRole(RoleDto dto, UUID id);
    List<RoleDto> getRoles();
    RoleDto getRoleById(UUID id);
}
