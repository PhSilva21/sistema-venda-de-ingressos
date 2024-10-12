package com.bandeira.sistema_venda_de_ingressos.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreatePlanDTO(


        @NotNull(message = "O nome não pode ser nulo")
        @NotBlank(message = "O nome não pode ser vazio")
        String name,


        @NotNull(message = "O preço não pode ser nulo")
        @NotBlank(message = "O preço não pode ser vazio")
        BigDecimal price
){
}
