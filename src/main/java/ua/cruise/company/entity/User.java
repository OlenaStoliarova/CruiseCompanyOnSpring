package ua.cruise.company.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table( name="users", uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class User implements UserDetails {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    @Size(min=5, message = "Email can't be shorter than 5 characters")
    private String email; //this is our login
    @Column(nullable = false)
    private String password;

    @Column(name = "first_name_en", nullable = false)
    private String firstNameEn;
    @Column(name = "last_name_en", nullable = false)
    private String lastNameEn;

    @Column(name = "first_name_native", nullable = false)
    private String firstNameNative;
    @Column(name = "last_name_native", nullable = false)
    private String lastNameNative;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<UserRole> userRole = new HashSet<>();
        userRole.add(role);
        return userRole;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true;}
}
