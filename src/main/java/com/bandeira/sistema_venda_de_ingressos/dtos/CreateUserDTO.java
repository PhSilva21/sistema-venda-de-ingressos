package com.bandeira.sistema_venda_de_ingressos.dtos;

import com.bandeira.sistema_venda_de_ingressos.models.enums.UserRole;

public record CreateUserDTO(

         String name,

         String email,

         String password,

         String cpf,

         UserRole userRole
) {
}
