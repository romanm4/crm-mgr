package com.crm.mgr.mapper;

import com.crm.mgr.dto.TodoDescDto;
import com.crm.mgr.entity.TodoDescEntity;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoDescMapper {
    TodoDescEntity dtoToEntity(TodoDescDto dto);
    TodoDescDto entityToDto(TodoDescEntity entity);
    List<TodoDescEntity> dtoListToEntity(List<TodoDescDto> dtoList);
    List<TodoDescDto> entityListToDto(List<TodoDescEntity> entityList);
}
