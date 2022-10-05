package com.crm.mgr.service.impl;

import com.crm.mgr.dto.LeadStatusDto;
import java.util.List;
import java.util.UUID;

public interface LeadStatusService {
    List<LeadStatusDto> getLeadStatuses();
    LeadStatusDto getLeadStatusById(UUID id);
}
