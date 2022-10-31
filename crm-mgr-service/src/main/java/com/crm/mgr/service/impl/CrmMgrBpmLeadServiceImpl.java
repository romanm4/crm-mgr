package com.crm.mgr.service.impl;

import com.crm.mgr.dto.LeadDto;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CrmMgrBpmLeadServiceImpl implements CrmMgrBpmLeadService {
    private final RuntimeService runtimeService;

    public CrmMgrBpmLeadServiceImpl(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @Override
    public ResponseEntity<?> invokeCreateLeadProcess(LeadDto dto) {
        try {
            String processInstanceId = UUID.randomUUID().toString();
            runtimeService.createProcessInstanceByKey("CrmMgrBpmLead")
                    .setVariable("leadRequest", dto)
                    .executeWithVariablesInReturn();
            return ResponseEntity.ok().body(processInstanceId + " is ready to eat.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknown Exception. Message: " + e.getMessage());
        }
    }
}
