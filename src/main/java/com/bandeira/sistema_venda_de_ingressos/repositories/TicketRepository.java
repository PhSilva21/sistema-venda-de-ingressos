package com.bandeira.sistema_venda_de_ingressos.repositories;

import com.bandeira.sistema_venda_de_ingressos.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
