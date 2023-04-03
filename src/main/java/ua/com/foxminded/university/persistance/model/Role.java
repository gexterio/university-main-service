package ua.com.foxminded.university.persistance.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    STUDENT,
    TEACHER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
