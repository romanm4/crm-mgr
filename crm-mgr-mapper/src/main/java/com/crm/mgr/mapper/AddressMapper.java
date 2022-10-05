package com.crm.mgr.mapper;

import com.crm.mgr.dto.AddressDto;
import com.crm.mgr.entity.AddressEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressEntity dtoToEntity(AddressDto dto);
    AddressDto entityToDto(AddressEntity entity);
    List<AddressEntity> dtoListToEntity(List<AddressDto> dtoList);
    List<AddressDto> entityListToDto(List<AddressEntity> entityList);
}
