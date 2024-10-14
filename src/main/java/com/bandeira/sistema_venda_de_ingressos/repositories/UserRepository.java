package com.bandeira.sistema_venda_de_ingressos.repositories;

import com.bandeira.sistema_venda_de_ingressos.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
