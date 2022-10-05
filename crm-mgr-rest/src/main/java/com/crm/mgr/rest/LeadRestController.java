package com.crm.mgr.rest;

import com.crm.mgr.dto.LeadDto;
import com.crm.mgr.service.impl.LeadService;
import com.crm.mgr.specification.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/lead")
public class LeadRestController {

    private final LeadService leadService;

    public LeadRestController(LeadService leadService) {
        this.leadService = leadService;
    }

    @PostMapping(path = "/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public Page<LeadDto> searchLead(@RequestBody List<SearchCriteria> searchCriteria, @RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return leadService.searchLead(searchCriteria, pageable);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public LeadDto createLead(@Valid @RequestBody  LeadDto dto) {
        return leadService.createLead(dto);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public LeadDto deleteLeadById(@PathVariable UUID id) {
        return leadService.deleteLeadById(id);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public LeadDto modifyLead(@Valid @RequestBody LeadDto dto, @PathVariable UUID id) {
        return leadService.modifyLead(dto, id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public List<LeadDto> getLeads() {
        return leadService.getLeads();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public LeadDto getLeadById(@PathVariable UUID id) {
        return leadService.getLeadById(id);
    }

    @PutMapping(path = "/{leadId}/status/{leadStatusId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public void assignLeadStatusToLead(@PathVariable UUID leadStatusId, @PathVariable UUID leadId) {
        leadService.assignLeadStatusToLead(leadStatusId, leadId);
    }

    @DeleteMapping(path = "/{leadId}/status/{leadStatusId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public void deleteLeadStatusLead(@PathVariable UUID leadStatusId, @PathVariable UUID leadId) {
        leadService.deleteLeadStatusLead(leadStatusId, leadId);
    }

    @PutMapping(path = "/{leadId}/task/{taskId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public void assignTaskToLead(@PathVariable UUID leadId, @PathVariable UUID taskId) {
        leadService.assignTaskToLead(leadId, taskId);
    }

    @DeleteMapping(path = "/{leadId}/task/{taskId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public void deleteTaskLead(@PathVariable UUID leadId, @PathVariable UUID taskId) {
        leadService.deleteTaskLead(leadId, taskId);
    }

    @PutMapping(path = "/{leadId}/address/{addressId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public void assignAddressToLead(@PathVariable UUID leadId, @PathVariable UUID addressId) {
        leadService.assignAddressToLead(leadId, addressId);
    }

    @DeleteMapping(path = "/{leadId}/address/{addressId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public void deleteAddressLead(@PathVariable UUID leadId, @PathVariable UUID addressId) {
        leadService.deleteAddressLead(leadId, addressId);
    }

    @PutMapping(path = "/{leadId}/user/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public void assignUserToLead(@PathVariable UUID leadId, @PathVariable UUID userId) {
        leadService.assignUserToLead(leadId, userId);
    }

    @DeleteMapping(path = "/{leadId}/user/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public void deleteUserLead(@PathVariable UUID leadId, @PathVariable UUID userId) {
        leadService.deleteUserLead(leadId, userId);
    }
}
