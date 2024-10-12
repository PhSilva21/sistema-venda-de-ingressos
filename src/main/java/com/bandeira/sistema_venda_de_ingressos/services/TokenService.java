package com.bandeira.sistema_venda_de_ingressos.services;

import com.bandeira.sistema_venda_de_ingressos.models.User;

import java.time.Instant;

public interface TokenService {

    public String generateToken(User user);

    public String validateToken(String token);

    public Instant getExpirationDate();
}
