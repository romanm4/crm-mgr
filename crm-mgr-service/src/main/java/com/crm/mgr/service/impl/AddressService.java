package com.crm.mgr.service.impl;

import com.crm.mgr.dto.AddressDto;
import com.crm.mgr.dto.LeadDto;

import java.util.List;
import java.util.UUID;

public interface AddressService {
    AddressDto createAddress(AddressDto dto);
    AddressDto deleteAddressById(UUID id);
    AddressDto modifyAddress(AddressDto dto, UUID id);
    List<AddressDto> getAddresses();
    AddressDto getAddressById(UUID id);
}
