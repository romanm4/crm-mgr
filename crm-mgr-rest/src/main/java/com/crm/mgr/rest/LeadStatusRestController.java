package com.crm.mgr.rest;

import com.crm.mgr.dto.LeadStatusDto;
import com.crm.mgr.service.impl.LeadStatusService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/lead-status")
public class LeadStatusRestController {
    private final LeadStatusService leadStatusService;

    public LeadStatusRestController(LeadStatusService leadStatusService) {
        this.leadStatusService = leadStatusService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public List<LeadStatusDto> getLeadStatuses() {
        return leadStatusService.getLeadStatuses();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public LeadStatusDto getLeadStatusById(@PathVariable UUID id) {
        return leadStatusService.getLeadStatusById(id);
    }
}
