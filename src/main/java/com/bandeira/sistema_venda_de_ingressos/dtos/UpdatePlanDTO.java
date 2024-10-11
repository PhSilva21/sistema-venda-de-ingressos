package com.bandeira.sistema_venda_de_ingressos.dtos;

import java.math.BigDecimal;

public record UpdatePlanDTO(

        Long id,

        String name,

        BigDecimal price
) {
}
