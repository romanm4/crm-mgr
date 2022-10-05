package com.crm.mgr.mapper;

import com.crm.mgr.dto.LeadStatusDto;
import com.crm.mgr.entity.LeadStatusEntity;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface LeadStatusMapper {
    LeadStatusEntity dtoToEntity(LeadStatusDto dto);
    LeadStatusDto entityToDto(LeadStatusEntity entity);
    List<LeadStatusEntity> dtoListToEntity(List<LeadStatusDto> dtoList);
    List<LeadStatusDto> entityListToDto(List<LeadStatusEntity> entityList);
}
