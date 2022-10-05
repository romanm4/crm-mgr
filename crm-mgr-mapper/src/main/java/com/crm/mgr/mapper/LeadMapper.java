package com.crm.mgr.mapper;

import com.crm.mgr.dto.LeadDto;
import com.crm.mgr.entity.LeadEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import java.util.List;

@Mapper(componentModel = "spring")
public interface LeadMapper {
    LeadEntity dtoToEntity(LeadDto dto);
    @Mappings({
            @Mapping(target = "salesRepId", source = "user.id"),
            @Mapping(target = "leadStatusId", source = "leadStatus.id")
    })
    LeadDto entityToDto(LeadEntity entity);
    List<LeadEntity> dtoListToEntity(List<LeadDto> dtoList);
    List<LeadDto> entityListToDto(List<LeadEntity> entityList);
}
