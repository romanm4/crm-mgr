package com.crm.mgr.mapper;

import com.crm.mgr.dto.TodoTypeDto;
import com.crm.mgr.entity.TodoTypeEntity;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoTypeMapper {
    TodoTypeEntity dtoToEntity(TodoTypeDto dto);
    TodoTypeDto entityToDto(TodoTypeEntity entity);
    List<TodoTypeEntity> dtoListToEntity(List<TodoTypeDto> dtoList);
    List<TodoTypeDto> entityListToDto(List<TodoTypeEntity> entityList);
}
