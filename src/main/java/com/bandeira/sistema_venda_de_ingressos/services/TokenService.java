package com.bandeira.sistema_venda_de_ingressos.services;

import com.bandeira.sistema_venda_de_ingressos.models.User;

import java.time.Instant;

public interface TokenService {


    String generateToken(User user);

    String validateToken(String token);

    Instant genExpirationDate();
}
