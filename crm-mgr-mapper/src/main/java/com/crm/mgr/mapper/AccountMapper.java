package com.crm.mgr.mapper;

import com.crm.mgr.dto.AccountDto;
import com.crm.mgr.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountEntity dtoToEntity(AccountDto dto);
    @Mappings({
            @Mapping(target = "userId", source = "user.id")
    })
    AccountDto entityToDto(AccountEntity entity);
    List<AccountEntity> dtoListToEntity(List<AccountDto> dtoList);
    List<AccountDto> entityListToDto(List<AccountEntity> entityList);
}
