package com.crm.mgr.mapper;

import com.crm.mgr.dto.JwtRoleDto;
import com.crm.mgr.dto.RoleDto;
import com.crm.mgr.entity.RoleEntity;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleEntity dtoToEntity(RoleDto dto);
    RoleDto entityToDto(RoleEntity entity);
    JwtRoleDto dtoToJwt(RoleDto dto);
    List<RoleEntity> dtoListToEntity(List<RoleDto> dtoList);
    List<RoleDto> entityListToDto(List<RoleEntity> entityList);
}
