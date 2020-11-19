package com.baxicar.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {

    USER(new HashSet<Permission>() {{
        add(Permission.DEVELOPERS_READ);
        add(Permission.DEVELOPERS_WRITE);

    }}),
    ADMIN(new HashSet<Permission>() {{
        add(Permission.DEVELOPERS_READ);
        add(Permission.DEVELOPERS_WRITE);
    }});

//    USER(Set.of(Permission.DEVELOPERS_READ)),
//    ADMIN(Set.of(Permission.DEVELOPERS_READ, Permission.DEVELOPERS_WRITE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
