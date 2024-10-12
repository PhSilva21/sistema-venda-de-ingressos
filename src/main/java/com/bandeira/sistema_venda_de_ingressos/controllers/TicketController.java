package com.bandeira.sistema_venda_de_ingressos.controllers;

import com.bandeira.sistema_venda_de_ingressos.dtos.CreateTicketDTO;
import com.bandeira.sistema_venda_de_ingressos.models.Ticket;
import com.bandeira.sistema_venda_de_ingressos.services.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(description = "Operação para comprar um ingreso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingresso comprado com sucesso"),
            @ApiResponse(responseCode = "417", description = "Erro na validação de dados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/buy")
    public ResponseEntity<Void> buyTicket(@RequestBody Long id) throws MessagingException, UnsupportedEncodingException, UnsupportedEncodingException {
        ticketService.buyTicket(id);
        return ResponseEntity.ok().build();
    }


    @Operation(description = "Operação para deletar todos os ingressos da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingressos deletados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllTickets() {
        ticketService.deleteAllTickets();
        return ResponseEntity.ok().build();
    }


    @Operation(description = "Operação para colocar ingressos do setor leste inferior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingressos criados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/insert-lowerEast")
    public ResponseEntity<Void> insertTicketLowerEast(@RequestBody CreateTicketDTO request) {
        ticketService.insertTicketLowerEast(request);
        return ResponseEntity.ok().build();
    }


    @Operation(description = "Operação para colocar ingressos do setor leste superior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingressos criados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/insert-upperEast")
    public ResponseEntity<Void> insertTicketUpperEast(@RequestBody CreateTicketDTO request) {
        ticketService.insertTicketUpperEast(request);
        return ResponseEntity.ok().build();
    }

    @Operation(description = "Operação para colocar ingressos do setor oeste inferior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingressos criados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/insert-lowerWest")
    public ResponseEntity<Void> insertTicketLowerWest(@RequestBody CreateTicketDTO request) {
        ticketService.insertTicketLowerWest(request);
        return ResponseEntity.ok().build();
    }


    @Operation(description = "Operação para colocar ingressos do setor oeste superior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingressos criados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/insert-upperWest")
    public ResponseEntity<Void> insertTicketUpperWest(@RequestBody CreateTicketDTO request) {
        ticketService.insertTicketUpperWest(request);
        return ResponseEntity.ok().build();
    }


    @Operation(description = "Operação para colocar ingressos do setor sul inferior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingressos criados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/insert-lowerSouth")
    public ResponseEntity<Void> insertTicketLowerSouth(@RequestBody CreateTicketDTO request) {
        ticketService.insertTicketLowerSouth(request);
        return ResponseEntity.ok().build();
    }


    @Operation(description = "Operação para colocar ingressos do setor sul superior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingressos criados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/insert-upperSouth")
    public ResponseEntity<Void> insertTicketUpperSouth(@RequestBody CreateTicketDTO request) {
        ticketService.insertTicketUpperSouth(request);
        return ResponseEntity.ok().build();
    }


    @Operation(description = "Operação para colocar ingressos do setor norte inferior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingressos criados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/insert-lowerNorth")
    public ResponseEntity<Void> insertTicketLowerNorth(@RequestBody CreateTicketDTO request) {
        ticketService.insertTicketLowerNorth(request);
        return ResponseEntity.ok().build();
    }


    @Operation(description = "Operação para colocar ingressos do setor norte superior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingressos criados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/insert-upperNorth")
    public ResponseEntity<Void> insertTicketUpperNorth(@RequestBody CreateTicketDTO request) {
        ticketService.insertTicketUpperNorth(request);
        return ResponseEntity.ok().build();
    }


    @Operation(description = "Operação para filtrar ingressos do setor leste inferior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornando lista com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/filter-lowerEast")
    public ResponseEntity<List<Ticket>> filterLowerEast() {
        var response = ticketService.filterLowerEast();
        return ResponseEntity.ok(response);
    }


    @Operation(description = "Operação para filtrar ingressos do setor leste superior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornando lista com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/filter-upperEast")
    public ResponseEntity<List<Ticket>> filterUpperEast() {
        var response = ticketService.filterUppersEast();
        return ResponseEntity.ok(response);
    }


    @Operation(description = "Operação para filtrar ingressos do setor oeste inferior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornando lista com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/filter-lowerWest")
    public ResponseEntity<List<Ticket>> filterLowerWest() {
        var response = ticketService.filterLowerWest();
        return ResponseEntity.ok(response);
    }


    @Operation(description = "Operação para filtrar ingressos do setor oeste superior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornando lista com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/filter-upperWest")
    public ResponseEntity<List<Ticket>> filterUpperWest() {
        var response = ticketService.filterUpperWest();
        return ResponseEntity.ok(response);
    }


    @Operation(description = "Operação para filtrar ingressos do setor sul inferior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornando lista com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/filter-lowerSouth")
    public ResponseEntity<List<Ticket>> filterLowerSouth() {
        var response = ticketService.filterLowerSouth();
        return ResponseEntity.ok(response);
    }


    @Operation(description = "Operação para filtrar ingressos do setor sul superior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornando lista com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/filter-upperSouth")
    public ResponseEntity<List<Ticket>> filterUpperSouth() {
        var response = ticketService.filterUpperSouth();
        return ResponseEntity.ok(response);
    }


    @Operation(description = "Operação para filtrar ingressos do setor norte inferior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornando lista com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/filter-lowerNorth")
    public ResponseEntity<List<Ticket>> filterLowerNorth() {
       var response = ticketService.filterLowerNorth();
        return ResponseEntity.ok(response);
    }


    @Operation(description = "Operação para filtrar ingressos do setor norte superior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornando lista com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/filter-upperNorth")
    public ResponseEntity<List<Ticket>> filterUpperNorth() {
        var response = ticketService.filterUpperNorth();
        return ResponseEntity.ok(response);
    }

}
