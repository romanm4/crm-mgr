package com.crm.mgr.service.impl;

import com.crm.mgr.dto.LeadDto;
import com.crm.mgr.entity.*;
import com.crm.mgr.mapper.LeadMapper;
import com.crm.mgr.repo.*;
import com.crm.mgr.specification.LeadSpecification;
import com.crm.mgr.specification.SearchCriteria;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "lead")
public class LeadServiceImpl implements LeadService {
    private final LeadRepository leadRepository;
    private final LeadStatusRepository leadStatusRepository;
    private final TaskRepository taskRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final LeadMapper leadMapper;

    public LeadServiceImpl(LeadRepository leadRepository, LeadStatusRepository leadStatusRepository, TaskRepository taskRepository, AddressRepository addressRepository, UserRepository userRepository, LeadMapper leadMapper) {
        this.leadRepository = leadRepository;
        this.leadStatusRepository = leadStatusRepository;
        this.taskRepository = taskRepository;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.leadMapper = leadMapper;
    }

    @Override
    public Page<LeadDto> searchLead(List<SearchCriteria> searchCriteria, Pageable pageable) {
        LeadSpecification leadSpecification = new LeadSpecification();
        searchCriteria.forEach(leadSpecification::add);
        Page<LeadEntity> leadEntities = leadRepository.findAll(leadSpecification, pageable);
        List<LeadDto> dtos = leadMapper.entityListToDto(leadEntities.getContent());
        return new PageImpl<>(dtos, pageable, leadEntities.getTotalElements());
    }

    @Override
    public LeadDto createLead(LeadDto dto) {
        LeadEntity entity = leadRepository.save(leadMapper.dtoToEntity(dto));
        return leadMapper.entityToDto(entity);
    }

    @Override
    public LeadDto deleteLeadById(UUID id) {
        LeadEntity entity = leadRepository.findById(id).orElseThrow();
        return leadMapper.entityToDto(entity);
    }

    @Override
    public LeadDto modifyLead(LeadDto dto, UUID id) {
        LeadEntity entity = leadRepository.findById(id).orElseThrow();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setMiddleName(dto.getMiddleName());
        entity.setTitle(dto.getTitle());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setIndustry(dto.getIndustry());
        entity.setBudget(dto.getBudget());
        entity.setCompany(dto.getCompany());
        entity.setLinkedInProfile(dto.getLinkedInProfile());
        entity.setBackgroundInfo(dto.getBackgroundInfo());
        entity.setRating(dto.getRating());
        entity.setProposalDueDate(dto.getProposalDueDate());
        entity.setDateOfInitialLead(dto.getDateOfInitialLead());
        entity = leadRepository.save(entity);
        return leadMapper.entityToDto(entity);
    }

    @Cacheable
    @Override
    public List<LeadDto> getLeads() {
        List<LeadEntity> entities = leadRepository.findAll();
        return leadMapper.entityListToDto(entities);
    }

    @Cacheable
    @Override
    public LeadDto getLeadById(UUID id) {
        LeadEntity entity = leadRepository.findById(id).orElseThrow();
        return leadMapper.entityToDto(entity);
    }

    @Override
    public void assignLeadStatusToLead(UUID leadStatusId, UUID leadId) {
        LeadStatusEntity leadStatus = leadStatusRepository.findById(leadStatusId).orElseThrow();
        LeadEntity lead = leadRepository.findById(leadId).orElseThrow();
        lead.setLeadStatus(leadStatus);
        leadRepository.save(lead);
    }

    @Override
    public void assignTaskToLead(UUID leadId, UUID taskId) {
        LeadEntity lead = leadRepository.findById(leadId).orElseThrow();
        TaskEntity task = taskRepository.findById(taskId).orElseThrow();
        lead.getTasks().add(task);
        leadRepository.save(lead);
    }

    @Override
    public void assignAddressToLead(UUID leadId, UUID addressId) {
        LeadEntity lead = leadRepository.findById(leadId).orElseThrow();
        AddressEntity address = addressRepository.findById(addressId).orElseThrow();
        lead.setAddress(address);
        leadRepository.save(lead);
    }

    @Override
    public void assignUserToLead(UUID leadId, UUID userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow();
        LeadEntity lead = leadRepository.findById(leadId).orElseThrow();
        lead.setUser(user);
        userRepository.save(user);
    }

    @Override
    public void deleteLeadStatusLead(UUID leadStatusId, UUID leadId) {
        LeadStatusEntity leadStatus = leadStatusRepository.findById(leadStatusId).orElseThrow();
        LeadEntity lead = leadRepository.findById(leadId).orElseThrow();
        lead.setLeadStatus(null);
        leadRepository.save(lead);
    }

    @Override
    public void deleteTaskLead(UUID leadId, UUID taskId) {
        LeadEntity lead = leadRepository.findById(leadId).orElseThrow();
        TaskEntity task = taskRepository.findById(taskId).orElseThrow();
        lead.getTasks().remove(task);
        leadRepository.save(lead);
    }

    @Override
    public void deleteAddressLead(UUID leadId, UUID addressId) {
        LeadEntity lead = leadRepository.findById(leadId).orElseThrow();
        AddressEntity address = addressRepository.findById(addressId).orElseThrow();
        lead.setAddress(null);
        leadRepository.save(lead);
    }

    @Override
    public void deleteUserLead(UUID leadId, UUID userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow();
        LeadEntity lead = leadRepository.findById(leadId).orElseThrow();
        lead.setUser(null);
        userRepository.save(user);
    }
}
