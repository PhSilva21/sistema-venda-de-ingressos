package com.bandeira.sistema_venda_de_ingressos.dtos;

import com.bandeira.sistema_venda_de_ingressos.models.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUserDTO(


         @NotNull(message = "O nome não pode ser nulo")
         @NotBlank(message = "O nome não pode ser vazio")
         String name,

         @NotNull(message = "Email não pode ser nulo")
         @NotBlank(message = "Email não pode ser vazio")
         @Email
         String email,

         @NotNull(message = "Senha não pode ser nula")
         @NotBlank(message = "Senha não pode ser vazia")
         @Size(min = 8, message = "A senha deve conter no minimo 8 caracteres")
         @Size(max = 12, message = "A senha deve conter no maximo 12 caracteres")
         String password,

         @NotNull(message = "Cpf não pode ser nulo")
         @NotBlank(message = "Cpf não pode ser vazio")
         String cpf,

         UserRole userRole
) {
}
