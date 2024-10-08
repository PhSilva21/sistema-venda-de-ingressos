package com.bandeira.sistema_venda_de_ingressos.dtos;

public record UpdatePasswordDTO(


        Long id,

        String password,

        String newPassword,

        String newPasswordConfirmation

) {
}
