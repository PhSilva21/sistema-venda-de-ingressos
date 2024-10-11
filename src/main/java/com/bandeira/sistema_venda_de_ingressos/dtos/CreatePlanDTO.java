package com.bandeira.sistema_venda_de_ingressos.dtos;

import java.math.BigDecimal;

public record CreatePlanDTO(


        String name,

        BigDecimal price
){
}
