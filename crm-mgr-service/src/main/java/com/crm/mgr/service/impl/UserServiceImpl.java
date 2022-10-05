package com.crm.mgr.service.impl;

import com.crm.mgr.dto.UserDto;
import com.crm.mgr.entity.RoleEntity;
import com.crm.mgr.entity.UserEntity;
import com.crm.mgr.entity.UserStatusEntity;
import com.crm.mgr.mapper.UserMapper;
import com.crm.mgr.repo.RoleRepository;
import com.crm.mgr.repo.UserRepository;
import com.crm.mgr.repo.UserStatusRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserStatusRepository userStatusRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserStatusRepository userStatusRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userStatusRepository = userStatusRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto createUser(UserDto dto) {
        UserEntity entity = userMapper.dtoToEntity(dto);
        setUserRole(dto.getRoleId(), entity);
        setUserStatus(dto.getUserStatusId(), entity);
        entity = userRepository.save(entity);
        return userMapper.entityToDto(entity);
    }

    private void setUserRole(UUID roleId, UserEntity entity) {
        if (Objects.nonNull(roleId)) {
            RoleEntity role = roleRepository.findById(roleId).orElseThrow();
            entity.setRole(role);
        }
    }

    private void setUserStatus(UUID userStatusId, UserEntity entity) {
        if (Objects.nonNull(userStatusId)) {
            UserStatusEntity userStatus = userStatusRepository.findById(userStatusId).orElseThrow();
            entity.setUserStatus(userStatus);
        }
    }

    @Override
    public UserDto deleteUserById(UUID id) {
        UserEntity entity = userRepository.findById(id).orElseThrow();
        return userMapper.entityToDto(entity);
    }

    @Override
    public UserDto modifyUser(UserDto dto, UUID id) {
        UserEntity entity = userRepository.findById(id).orElseThrow();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setMiddleName(dto.getMiddleName());
        entity.setEmail(dto.getEmail());
        entity = userRepository.save(entity);
        return userMapper.entityToDto(entity);
    }

    @Cacheable
    @Override
    public List<UserDto> getUsers() {
        List<UserEntity> entities = userRepository.findAll();
        return userMapper.entityListToDto(entities);
    }

    @Cacheable
    @Override
    public UserDto getUserById(UUID id) {
        UserEntity entity = userRepository.findById(id).orElseThrow();
        return userMapper.entityToDto(entity);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        UserEntity entity = userRepository.findByEmail(email).orElseThrow();
        return userMapper.entityToDto(entity);
    }

    @Override
    public void assignRoleToUser(UUID roleId, UUID userId) {
        RoleEntity role = roleRepository.findById(roleId).orElseThrow();
        UserEntity user = userRepository.findById(userId).orElseThrow();
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public void assignUserStatusToUser(UUID statusId, UUID userId) {
        UserStatusEntity userStatus = userStatusRepository.findById(statusId).orElseThrow();
        UserEntity user = userRepository.findById(userId).orElseThrow();
        user.setUserStatus(userStatus);
        userRepository.save(user);
    }
}
