package com.crm.mgr.service.impl;

import com.crm.mgr.dto.RoleDto;
import com.crm.mgr.entity.RoleEntity;
import com.crm.mgr.mapper.RoleMapper;
import com.crm.mgr.repo.RoleRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "role")
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleDto createRole(RoleDto dto) {
        RoleEntity entity = roleRepository.save(roleMapper.dtoToEntity(dto));
        return roleMapper.entityToDto(entity);
    }

    @Override
    public RoleDto deleteRoleById(UUID id) {
        RoleEntity entity = roleRepository.findById(id).orElseThrow();
        return roleMapper.entityToDto(entity);
    }

    @Override
    public RoleDto modifyRole(RoleDto dto, UUID id) {
        RoleEntity entity = roleRepository.findById(id).orElseThrow();
        entity.setRole(dto.getRole());
        entity = roleRepository.save(entity);
        return roleMapper.entityToDto(entity);
    }

    @Cacheable
    @Override
    public List<RoleDto> getRoles() {
        List<RoleEntity> entities = roleRepository.findAll();
        return roleMapper.entityListToDto(entities);
    }

    @Cacheable
    @Override
    public RoleDto getRoleById(UUID id) {
        RoleEntity entity = roleRepository.findById(id).orElseThrow();
        return roleMapper.entityToDto(entity);
    }
}
