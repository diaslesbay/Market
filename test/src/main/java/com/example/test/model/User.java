package com.example.test.model;

import com.example.test.enums.TypeOfUser;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

//    @NotBlank(message = "Username should not be empty")
//    @Size(min = 2, max = 20, message = "Username length should be from 2 to 20 characters")
    @Column(name = "username",unique = true)
    private String username;

//    @NotBlank(message = "Email is required")
//    @Email(message = "Email must be in the format example@example.com")
//    @Pattern(regexp = "\\w+@\\w+\\.\\w+", message = "Email must be in the format example@example.example")
    @Column(name = "email",unique = true)
    private String email;

//    @NotBlank(message = "Password is required")
//    @Size(min = 8, message = "Password must be at least 8 characters long")
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "Password must contain at least one lowercase letter, one uppercase letter, and one digit")
    @Column(name = "password")
    private String password;

//    @NotBlank(message = "Firstname cannot be empty")
    @Column(name = "firstname")
    private String firstname;

//    @NotBlank(message = "Lastname cannot be empty")
    @Column(name = "lastname")
    private String lastname;

    @Column(name = "address")
    private String address;

//    @NotBlank(message = "Phone number is required")
//    @Pattern(regexp = "\\+77\\d{2}-\\d{3}-\\d{2}-\\d{2}", message = "Phone number's format should be '+77xx-xxx-xx-xx'")
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;


    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TypeOfUser status;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(status.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
