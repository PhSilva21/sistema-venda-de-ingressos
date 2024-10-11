package com.bandeira.sistema_venda_de_ingressos.controllers;

import com.bandeira.sistema_venda_de_ingressos.dtos.CreateTicketDTO;
import com.bandeira.sistema_venda_de_ingressos.models.Ticket;
import com.bandeira.sistema_venda_de_ingressos.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/insert")
    public ResponseEntity<Void> insertTicket(@RequestBody CreateTicketDTO request){
        ticketService.insertTicketLowerEast(request);
        return ResponseEntity.ok().build();
    }
    
}
