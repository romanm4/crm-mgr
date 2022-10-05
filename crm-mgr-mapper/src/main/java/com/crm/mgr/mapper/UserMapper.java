package com.crm.mgr.mapper;

import com.crm.mgr.dto.UserDto;
import com.crm.mgr.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity dtoToEntity(UserDto dto);
    @Mappings({
            @Mapping(target = "roleId", source = "role.id"),
            @Mapping(target = "userStatusId", source = "userStatus.id")
    })
    UserDto entityToDto(UserEntity entity);
    List<UserEntity> dtoListToEntity(List<UserDto> dtoList);
    List<UserDto> entityListToDto(List<UserEntity> entityList);
}
