package com.bandeira.sistema_venda_de_ingressos.controllers;

import com.bandeira.sistema_venda_de_ingressos.dtos.BuyTicketDTO;
import com.bandeira.sistema_venda_de_ingressos.models.Ticket;
import com.bandeira.sistema_venda_de_ingressos.services.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
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


    @Operation(description = "Operação para comprar um ingreso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingresso comprado com sucesso"),
            @ApiResponse(responseCode = "417", description = "Erro na validação de dados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/buy")
    public ResponseEntity<Void> buyTicket(@RequestBody @Valid BuyTicketDTO request) throws MessagingException, UnsupportedEncodingException, UnsupportedEncodingException {
        ticketService.buyTicket(request);
        return ResponseEntity.ok().build();
    }

    @Operation(description = "Operação para filtrar ingressos do setor leste inferior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornando lista com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/filter-lower-east")
    public ResponseEntity<List<Ticket>> filterLowerEast() {
        var response = ticketService.filterLowerEast();
        return ResponseEntity.ok(response);
    }


    @Operation(description = "Operação para filtrar ingressos do setor leste superior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornando lista com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/filter-upper-east")
    public ResponseEntity<List<Ticket>> filterUpperEast() {
        var response = ticketService.filterUppersEast();
        return ResponseEntity.ok(response);
    }


    @Operation(description = "Operação para filtrar ingressos do setor oeste inferior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornando lista com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/filter-lower-west")
    public ResponseEntity<List<Ticket>> filterLowerWest() {
        var response = ticketService.filterLowerWest();
        return ResponseEntity.ok(response);
    }


    @Operation(description = "Operação para filtrar ingressos do setor oeste superior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornando lista com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/filter-upper-west")
    public ResponseEntity<List<Ticket>> filterUpperWest() {
        var response = ticketService.filterUpperWest();
        return ResponseEntity.ok(response);
    }


    @Operation(description = "Operação para filtrar ingressos do setor sul inferior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornando lista com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/filter-lower-south")
    public ResponseEntity<List<Ticket>> filterLowerSouth() {
        var response = ticketService.filterLowerSouth();
        return ResponseEntity.ok(response);
    }


    @Operation(description = "Operação para filtrar ingressos do setor sul superior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornando lista com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/filter-upper-south")
    public ResponseEntity<List<Ticket>> filterUpperSouth() {
        var response = ticketService.filterUpperSouth();
        return ResponseEntity.ok(response);
    }


    @Operation(description = "Operação para filtrar ingressos do setor norte inferior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornando lista com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/filter-lower-north")
    public ResponseEntity<List<Ticket>> filterLowerNorth() {
       var response = ticketService.filterLowerNorth();
        return ResponseEntity.ok(response);
    }


    @Operation(description = "Operação para filtrar ingressos do setor norte superior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornando lista com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/filter-upper-north")
    public ResponseEntity<List<Ticket>> filterUpperNorth() {
        var response = ticketService.filterUpperNorth();
        return ResponseEntity.ok(response);
    }

}
