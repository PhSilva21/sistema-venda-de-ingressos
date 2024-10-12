package com.bandeira.sistema_venda_de_ingressos.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateEmailDTO(

        @NotNull(message = "O id do usuário não pode ser nulo")
        @NotBlank(message = "O id do usuário não pode ser vazio")
        Long id,

        @NotNull(message = "O novo email não pode ser nulo")
        @NotBlank(message = "O novo email não pode ser vazio")
        String email,

        @NotNull(message = "o código de confirmação não pode ser nulo")
        @NotBlank(message = "O código de confirmção não pode ser vazio")
        String code
) {
}
