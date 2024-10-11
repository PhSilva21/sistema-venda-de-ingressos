package com.bandeira.sistema_venda_de_ingressos.dtos;

import com.bandeira.sistema_venda_de_ingressos.models.enums.SectorTicket;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateTicketDTO(

        BigDecimal price,

        Integer numberOfTickets,

        LocalDate date
) {
}
