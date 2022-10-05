package com.crm.mgr.mapper;

import com.crm.mgr.dto.UserStatusDto;
import com.crm.mgr.entity.UserStatusEntity;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserStatusMapper {
    UserStatusEntity dtoToEntity(UserStatusDto dto);
    UserStatusDto entityToDto(UserStatusEntity entity);
    List<UserStatusEntity> dtoListToEntity(List<UserStatusDto> dtoList);
    List<UserStatusDto> entityListToDto(List<UserStatusEntity> entityList);
}
