package com.crm.mgr.dto;

import java.io.Serializable;
import java.util.UUID;

public class RoleDto implements Serializable {
    private UUID id;
    private String role;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
