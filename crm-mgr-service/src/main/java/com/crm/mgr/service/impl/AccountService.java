package com.crm.mgr.service.impl;

import com.crm.mgr.dto.AccountDto;
import java.util.List;
import java.util.UUID;

public interface AccountService {
    AccountDto createAccount(AccountDto dto);
    AccountDto deleteAccountById(UUID id);
    AccountDto modifyAccount(AccountDto dto, UUID id);
    List<AccountDto> getAccounts();
    AccountDto getAccountById(UUID id);
    AccountDto getAccountByLogin(String login);
    void assignAccountToUser(UUID accountId, UUID userId);
    void deleteAccountUser(UUID accountId, UUID userId);
}
