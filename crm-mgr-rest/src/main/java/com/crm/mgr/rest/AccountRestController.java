package com.crm.mgr.rest;

import com.crm.mgr.dto.AccountDto;
import com.crm.mgr.service.impl.AccountService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/account")
@CrossOrigin
public class AccountRestController {
    private final AccountService accountService;

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasAuthority('Admin')")
    public AccountDto createAccount(@Valid @RequestBody AccountDto dto) {
        return accountService.createAccount(dto);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public AccountDto deleteAccountById(@PathVariable UUID id) {
        return accountService.deleteAccountById(id);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('Sales Rep') or hasAuthority('Sales Manager')")
    public AccountDto modifyAccount(@Valid @RequestBody AccountDto dto, @PathVariable UUID id) {
        return accountService.modifyAccount(dto, id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AccountDto> getAccounts() {
        return accountService.getAccounts();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AccountDto getAccountById(@PathVariable UUID id) {
        return accountService.getAccountById(id);
    }

    @PutMapping(path = "/{accountId}/user/{userId}")
    @PreAuthorize("hasAuthority('Admin')")
    public void assignAccountToUser(@PathVariable UUID accountId, @PathVariable UUID userId) {
        accountService.assignAccountToUser(accountId, userId);
    }

    @DeleteMapping(path = "/{accountId}/user/{userId}")
    @PreAuthorize("hasAuthority('Admin')")
    public void deleteAccountUser(@PathVariable UUID accountId, @PathVariable UUID userId) {
        accountService.deleteAccountUser(accountId, userId);
    }
}
