package com.bandeira.sistema_venda_de_ingressos.controllers;

import com.bandeira.sistema_venda_de_ingressos.dtos.BuyTicketDTO;
import com.bandeira.sistema_venda_de_ingressos.dtos.CreateTicketDTO;
import com.bandeira.sistema_venda_de_ingressos.models.Ticket;
import com.bandeira.sistema_venda_de_ingressos.services.TicketService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

import java.util.List;

@RestController
@RequestMapping("api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    
    @PostMapping("/buy")
    public ResponseEntity<Void> buyTicket(@RequestBody BuyTicketDTO request) throws MessagingException, UnsupportedEncodingException, UnsupportedEncodingException {
        ticketService.buyTicket(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllTickets() {
        ticketService.deleteAllTickets();
        return ResponseEntity.ok().build();
    }


    @PostMapping("/insert-lowerEast")
    public ResponseEntity<Void> insertTicketLowerEast(@RequestBody CreateTicketDTO request) {
        ticketService.insertTicketLowerEast(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/insert-upperEast")
    public ResponseEntity<Void> insertTicketUpperEast(@RequestBody CreateTicketDTO request) {
        ticketService.insertTicketUpperEast(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/insert-lowerWest")
    public ResponseEntity<Void> insertTicketLowerWest(@RequestBody CreateTicketDTO request) {
        ticketService.insertTicketLowerWest(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/insert-upperWest")
    public ResponseEntity<Void> insertTicketUpperWest(@RequestBody CreateTicketDTO request) {
        ticketService.insertTicketUpperWest(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/insert-lowerSouth")
    public ResponseEntity<Void> insertTicketLowerSouth(@RequestBody CreateTicketDTO request) {
        ticketService.insertTicketLowerSouth(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/insert-upperSouth")
    public ResponseEntity<Void> insertTicketUpperSouth(@RequestBody CreateTicketDTO request) {
        ticketService.insertTicketUpperSouth(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/insert-lowerNorth")
    public ResponseEntity<Void> insertTicketLowerNorth(@RequestBody CreateTicketDTO request) {
        ticketService.insertTicketLowerNorth(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/insert-upperNorth")
    public ResponseEntity<Void> insertTicketUpperNorth(@RequestBody CreateTicketDTO request) {
        ticketService.insertTicketUpperNorth(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filter-lowerEast")
    public ResponseEntity<List<Ticket>> filterLowerEast() {
       var response = ticketService.filterLowerEast();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filter-upperEast")
    public ResponseEntity<List<Ticket>> filterUpperEast() {
       var response = ticketService.filterUppersEast();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filter-lowerWest")
    public ResponseEntity<List<Ticket>> filterLowerWest() {
       var response = ticketService.filterLowerWest();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filter-upperWest")
    public ResponseEntity<List<Ticket>> filterUpperWest() {
       var response = ticketService.filterUpperWest();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filter-lowerSouth")
    public ResponseEntity<List<Ticket>> filterLowerSouth() {
       var response = ticketService.filterLowerSouth();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filter-upperSouth")
    public ResponseEntity<List<Ticket>> filterUpperSouth() {
       var response = ticketService.filterUpperSouth();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filter-lowerNorth")
    public ResponseEntity<List<Ticket>> filterLowerNorth() {
       var response = ticketService.filterLowerNorth();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filter-upperNorth")
    public ResponseEntity<List<Ticket>> filterUpperNorth() {
       var response = ticketService.filterUpperNorth();
        return ResponseEntity.ok(response);
    }

}
