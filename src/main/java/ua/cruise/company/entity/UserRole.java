package ua.cruise.company.entity;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_TRAVEL_AGENT,
    ROLE_TOURIST;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
