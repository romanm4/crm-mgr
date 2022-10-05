package com.crm.mgr.service.impl;

import com.crm.mgr.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDto createUser(UserDto dto);
    UserDto deleteUserById(UUID id);
    UserDto modifyUser(UserDto dto, UUID id);
    List<UserDto> getUsers();
    UserDto getUserById(UUID id);
    UserDto getUserByEmail(String email);
    void assignRoleToUser(UUID roleId, UUID userId);
    void assignUserStatusToUser(UUID statusId, UUID userId);
}
