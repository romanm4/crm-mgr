package com.crm.mgr.dto;

import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.UUID;

public class UserDto implements Serializable {
    private UUID id;
    private String firstName;
    private String lastName;
    private String middleName;
    @Email
    private String email;
    private UUID roleId;
    private UUID userStatusId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getRoleId() {
        return roleId;
    }

    public void setRoleId(UUID roleId) {
        this.roleId = roleId;
    }

    public UUID getUserStatusId() {
        return userStatusId;
    }

    public void setUserStatusId(UUID userStatusId) {
        this.userStatusId = userStatusId;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", email='" + email + '\'' +
                ", roleId=" + roleId +
                ", userStatusId=" + userStatusId +
                '}';
    }
}
