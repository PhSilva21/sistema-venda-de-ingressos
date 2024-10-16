package com.bandeira.sistema_venda_de_ingressos.models;
import com.bandeira.sistema_venda_de_ingressos.models.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "users")
@Entity
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @Column(unique = true)
    private String email;


    private String password;

    @Column(unique = true)
    private String cpf;


    private UserRole userRole;


    private Long points;


    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Plan> vipsUsers = new ArrayList<>();


    public User(String name, String email, String password, String cpf, UserRole userRole) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.userRole = userRole;
    }

    public User(Long id, String name, String email, String password, String cpf, UserRole userRole) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.userRole = userRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.userRole == UserRole.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
