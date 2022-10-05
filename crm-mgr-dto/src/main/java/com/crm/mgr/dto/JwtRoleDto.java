package com.crm.mgr.dto;

import org.springframework.security.core.GrantedAuthority;

public class JwtRoleDto implements GrantedAuthority {
    private String role;

    @Override
    public String getAuthority() {
        return role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "JwtRoleDto{" +
                "role='" + role + '\'' +
                '}';
    }
}
