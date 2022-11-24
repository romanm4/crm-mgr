package com.crm.mgr.service.impl;

import com.crm.mgr.dto.JwtTokenDto;

public interface JwtService {
    JwtTokenDto signIn(String login, String password) throws Exception;
}
