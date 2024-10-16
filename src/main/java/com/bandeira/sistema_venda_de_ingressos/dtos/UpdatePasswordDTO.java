package com.bandeira.sistema_venda_de_ingressos.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdatePasswordDTO(


        @NotNull(message = "O id não pode ser nulo")
        @NotBlank(message = "O id não pode ser vazio")
        Long id,

        @NotNull(message = "A senha não pode ser nula")
        @NotBlank(message = "A senha não pode ser vazia")
        String code,

        @NotNull(message = "A nova senha não pode ser nula")
        @NotBlank(message = "A nova senha não pode ser vazia")
        String newPassword,

        @NotNull(message = "A confirmação da senha não pode ser nula")
        @NotBlank(message = "A confirmação da senha não pode ser vazia")
        String newPasswordConfirmation

) {
}
