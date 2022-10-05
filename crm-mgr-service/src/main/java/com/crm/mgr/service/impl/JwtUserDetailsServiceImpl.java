package com.crm.mgr.service.impl;

import com.crm.mgr.dto.AccountDto;
import com.crm.mgr.dto.JwtRoleDto;
import com.crm.mgr.dto.RoleDto;
import com.crm.mgr.dto.UserDto;
import com.crm.mgr.mapper.RoleMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    private final AccountService accountService;
    private final UserService userService;
    private final RoleService roleService;
    private final RoleMapper roleMapper;

    public JwtUserDetailsServiceImpl(AccountService accountService, UserService userService, RoleService roleService, RoleMapper roleMapper) {
        this.accountService = accountService;
        this.userService = userService;
        this.roleService = roleService;
        this.roleMapper = roleMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountDto accountDto = accountService.getAccountByLogin(username);
        UserDto userDto = userService.getUserById(accountDto.getUserId());
        RoleDto roleDto = roleService.getRoleById(userDto.getRoleId());
        JwtRoleDto jwtRoleDto = roleMapper.dtoToJwt(roleDto);
        if (accountDto.getLogin().equals(username)) {
            return new User(accountDto.getLogin(), accountDto.getPassword(), List.of(jwtRoleDto));
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
