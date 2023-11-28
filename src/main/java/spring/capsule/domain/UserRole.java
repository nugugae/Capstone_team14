package spring.capsule.domain;

public enum UserRole {
    USER,ADMIN
}
/*
@Column(name = "role", nullable = false)
@Enumerated(EnumType.STRING)
private UserRole role;

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
}
 */