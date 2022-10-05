package com.crm.mgr.service.impl;

import com.crm.mgr.dto.AddressDto;
import com.crm.mgr.entity.AddressEntity;
import com.crm.mgr.mapper.AddressMapper;
import com.crm.mgr.repo.AddressRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "address")
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public AddressDto createAddress(AddressDto dto) {
        AddressEntity entity = addressRepository.save(addressMapper.dtoToEntity(dto));
        return addressMapper.entityToDto(entity);
    }

    @Override
    public AddressDto deleteAddressById(UUID id) {
        AddressEntity entity = addressRepository.findById(id).orElseThrow();
        return addressMapper.entityToDto(entity);
    }

    @Override
    public AddressDto modifyAddress(AddressDto dto, UUID id) {
        AddressEntity entity = addressRepository.findById(id).orElseThrow();
        entity.setStreet(dto.getStreet());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
        entity.setCountry(dto.getCountry());
        entity.setZip(dto.getZip());
        entity = addressRepository.save(entity);
        return addressMapper.entityToDto(entity);
    }

    @Cacheable
    @Override
    public List<AddressDto> getAddresses() {
        List<AddressEntity> entities = addressRepository.findAll();
        return addressMapper.entityListToDto(entities);
    }

    @Cacheable
    @Override
    public AddressDto getAddressById(UUID id) {
        AddressEntity entity = addressRepository.findById(id).orElseThrow();
        return addressMapper.entityToDto(entity);
    }
}
