package com.bandeira.sistema_venda_de_ingressos.dtos;

import com.bandeira.sistema_venda_de_ingressos.models.enums.SectorTicket;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateTicketDTO(

        @NotNull(message = "o preço não pode ser nulo")
        @NotBlank(message = "O preço não pode ser vazio")
        BigDecimal price,

        @NotNull(message = "O número de ingressos não pode ser nulo")
        @NotBlank(message = "O número de ingressos não pode ser vazio")
        Integer numberOfTickets,

        @NotNull(message = "A data do evento não pode ser nula")
        @NotBlank(message = "A data do evento não pode ser vazia")
        LocalDate date
) {
}
