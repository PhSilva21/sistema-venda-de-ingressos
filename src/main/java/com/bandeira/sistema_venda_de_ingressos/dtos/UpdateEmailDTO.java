package com.bandeira.sistema_venda_de_ingressos.dtos;

public record UpdateEmailDTO(

        Long id,

        String email,

        String code
) {
}
