package com.bandeira.sistema_venda_de_ingressos.models;
import com.bandeira.sistema_venda_de_ingressos.models.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_users")
@Entity
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;


    private String email;


    private String password;


    private String cpf;


    private UserRole userRole;


    private Long points;

    @OneToMany(mappedBy = "user")
    List<Ticket> tickets = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Plan> vipsUsers = new ArrayList<>();


    public User(String name, String email, String password, String cpf, UserRole userRole) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.userRole = userRole;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
