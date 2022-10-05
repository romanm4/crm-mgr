package com.crm.mgr.service.impl;

import com.crm.mgr.dto.UserStatusDto;
import java.util.List;
import java.util.UUID;

public interface UserStatusService {
    List<UserStatusDto> getUserStatuses();
    UserStatusDto getUserStatusById(UUID id);
}
