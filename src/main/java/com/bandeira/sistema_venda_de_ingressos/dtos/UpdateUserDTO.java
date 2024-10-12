package com.bandeira.sistema_venda_de_ingressos.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateUserDTO(

        @NotNull(message = "O id não pode ser nulo")
        @NotBlank(message = "O id não pode ser vazio")
        Long id,

        @NotNull(message = "O nome não pode ser nulo")
        @NotBlank(message = "O nome não pode ser vazio")
        String name,

        @NotNull(message = "Cpf não pode ser nulo")
        @NotBlank(message = "Cpf não pode ser vazio")
        String cpf
) {
}
