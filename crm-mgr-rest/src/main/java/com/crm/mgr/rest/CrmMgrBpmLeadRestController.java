package com.crm.mgr.rest;

import com.crm.mgr.dto.LeadDto;
import com.crm.mgr.service.impl.CrmMgrBpmLeadService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/bpm/lead")
public class CrmMgrBpmLeadRestController {

    private final CrmMgrBpmLeadService crmMgrBpmLeadService;

    public CrmMgrBpmLeadRestController(CrmMgrBpmLeadService crmMgrBpmLeadService) {
        this.crmMgrBpmLeadService = crmMgrBpmLeadService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public ResponseEntity<?> invokeCreateLeadProcess(@Valid @RequestBody LeadDto dto) {
        return crmMgrBpmLeadService.invokeCreateLeadProcess(dto);
    }
}
