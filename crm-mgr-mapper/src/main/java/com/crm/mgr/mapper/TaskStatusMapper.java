package com.crm.mgr.mapper;

import com.crm.mgr.dto.TaskStatusDto;
import com.crm.mgr.entity.TaskStatusEntity;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskStatusMapper {
    TaskStatusEntity dtoToEntity(TaskStatusDto dto);
    TaskStatusDto entityToDto(TaskStatusEntity entity);
    List<TaskStatusEntity> dtoListToEntity(List<TaskStatusDto> dtoList);
    List<TaskStatusDto> entityListToDto(List<TaskStatusEntity> entityList);
}
