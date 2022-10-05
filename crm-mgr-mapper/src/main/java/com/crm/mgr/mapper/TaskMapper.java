package com.crm.mgr.mapper;

import com.crm.mgr.dto.TaskDto;
import com.crm.mgr.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskEntity dtoToEntity(TaskDto dto);
    @Mappings({
            @Mapping(target = "leadId", source = "lead.id"),
            @Mapping(target = "todoTypeId", source = "todoType.id"),
            @Mapping(target = "todoDescId", source = "todoDesc.id"),
            @Mapping(target = "userId", source = "user.id"),
            @Mapping(target = "statusId", source = "taskStatus.id")
    })
    TaskDto entityToDto(TaskEntity entity);
    List<TaskEntity> dtoListToEntity(List<TaskDto> dtoList);
    List<TaskDto> entityListToDto(List<TaskEntity> entityList);
}
