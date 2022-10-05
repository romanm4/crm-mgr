package com.crm.mgr.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.crm.mgr.dto.LeadDto;
import com.crm.mgr.specification.SearchCriteria;
import java.util.List;
import java.util.UUID;

public interface LeadService {
    Page<LeadDto> searchLead(List<SearchCriteria> searchCriteria, Pageable pageable);
    LeadDto createLead(LeadDto dto);
    LeadDto deleteLeadById(UUID id);
    LeadDto modifyLead(LeadDto dto, UUID id);
    List<LeadDto> getLeads();
    LeadDto getLeadById(UUID id);
    void assignLeadStatusToLead(UUID leadStatusId, UUID leadId);
    void deleteLeadStatusLead(UUID leadStatusId, UUID leadId);
    void assignTaskToLead(UUID leadId, UUID taskId);
    void deleteTaskLead(UUID leadId, UUID taskId);
    void assignAddressToLead(UUID leadId, UUID addressId);
    void deleteAddressLead(UUID leadId, UUID addressId);
    void assignUserToLead(UUID leadId, UUID userId);
    void deleteUserLead(UUID leadId, UUID userId);
}
