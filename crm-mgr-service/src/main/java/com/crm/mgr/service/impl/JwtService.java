package com.crm.mgr.service.impl;

import com.crm.mgr.dto.AccountDto;

public interface JwtService {
    String signIn(String login, String password) throws Exception;
}
