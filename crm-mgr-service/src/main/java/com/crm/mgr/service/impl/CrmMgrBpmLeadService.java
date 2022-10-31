package com.crm.mgr.service.impl;

import com.crm.mgr.dto.LeadDto;
import org.springframework.http.ResponseEntity;

public interface CrmMgrBpmLeadService {
    ResponseEntity<?> invokeCreateLeadProcess(LeadDto dto);
}
