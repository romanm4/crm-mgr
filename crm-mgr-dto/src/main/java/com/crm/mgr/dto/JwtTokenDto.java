package com.crm.mgr.dto;

import java.util.List;

public class JwtTokenDto {
    private String sub;
    private List<JwtRoleDto> roles;
    private Long exp;
    private Long iat;
    private String decodedToken;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public List<JwtRoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<JwtRoleDto> roles) {
        this.roles = roles;
    }

    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }

    public Long getIat() {
        return iat;
    }

    public void setIat(Long iat) {
        this.iat = iat;
    }

    public String getDecodedToken() {
        return decodedToken;
    }

    public void setDecodedToken(String decodedToken) {
        this.decodedToken = decodedToken;
    }

    @Override
    public String toString() {
        return "JwtTokenDto{" +
                "sub='" + sub + '\'' +
                ", roles=" + roles +
                ", exp=" + exp +
                ", iat=" + iat +
                ", decodedToken='" + decodedToken + '\'' +
                '}';
    }
}
