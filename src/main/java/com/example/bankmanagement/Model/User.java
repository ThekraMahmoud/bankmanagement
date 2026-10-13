package com.example.bankmanagement.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true,nullable = false,length = 10)
    private String userName;


    @Column(nullable = false,length = 20)
    private String name;

    @Column(nullable = false)
    private String Password;

    @Column(unique = true,nullable = false)
    private String email;


    @Pattern(regexp = "^(CUSTOMER|EMPLOYEE|ADMIN)$")
    @Column(nullable = false)
    private String role;


    //
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Customer customer;


    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Employee employee;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + this.role));
    }

    @Override
    public String getUsername() {
        return this.userName;
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
