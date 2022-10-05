package com.crm.mgr.service.impl;

import com.crm.mgr.dto.AccountDto;
import com.crm.mgr.entity.AccountEntity;
import com.crm.mgr.entity.UserEntity;
import com.crm.mgr.mapper.AccountMapper;
import com.crm.mgr.repo.AccountRepository;
import com.crm.mgr.repo.UserRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = "account")
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository, AccountMapper accountMapper, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.accountMapper = accountMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AccountDto createAccount(AccountDto dto) {
        AccountEntity entity = accountMapper.dtoToEntity(dto);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        setAccountUser(dto.getUserId(), entity);
        entity = accountRepository.save(entity);
        return accountMapper.entityToDto(entity);
    }

    private void setAccountUser(UUID userId, AccountEntity entity) {
        if (Objects.nonNull(userId)) {
            UserEntity user = userRepository.findById(userId).orElseThrow();
            entity.setUser(user);
        }
    }

    @Override
    public AccountDto deleteAccountById(UUID id) {
        AccountEntity entity = accountRepository.findById(id).orElseThrow();
        return accountMapper.entityToDto(entity);
    }

    @Override
    public AccountDto modifyAccount(AccountDto dto, UUID id) {
        AccountEntity entity = accountRepository.findById(id).orElseThrow();
        entity.setLogin(dto.getLogin());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        return accountMapper.entityToDto(entity);
    }

    @Override
    public List<AccountDto> getAccounts() {
        List<AccountEntity> entities = accountRepository.findAll();
        return accountMapper.entityListToDto(entities);
    }

    @Override
    public AccountDto getAccountById(UUID id) {
        AccountEntity entity = accountRepository.findById(id).orElseThrow();
        return accountMapper.entityToDto(entity);
    }

    @Override
    public AccountDto getAccountByLogin(String login) {
        AccountEntity entity = accountRepository.findByLogin(login).orElseThrow();
        return accountMapper.entityToDto(entity);
    }

    @Override
    public void assignAccountToUser(UUID accountId, UUID userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow();
        AccountEntity account = accountRepository.findById(accountId).orElseThrow();
        user.setAccount(account);
        userRepository.save(user);
    }

    @Override
    public void deleteAccountUser(UUID accountId, UUID userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow();
        user.setAccount(null);
        userRepository.save(user);
    }
}
