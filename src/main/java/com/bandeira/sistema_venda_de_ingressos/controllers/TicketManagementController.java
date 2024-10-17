package com.bandeira.sistema_venda_de_ingressos.controllers;

import com.bandeira.sistema_venda_de_ingressos.dtos.CreateTicketDTO;
import com.bandeira.sistema_venda_de_ingressos.models.Ticket;
import com.bandeira.sistema_venda_de_ingressos.services.TicketManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/management")
@RequiredArgsConstructor
public class TicketManagementController {

    public final TicketManagementService ticketManagementService;


    @Operation(description = "Operação para colocar ingressos do setor leste inferior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingressos criados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/insert-lower-east")
    public ResponseEntity<List<Ticket>> insertTicketLowerEast(@RequestBody @Valid CreateTicketDTO request) {
        var response = ticketManagementService.insertTicketLowerEast(request);
        return ResponseEntity.ok().body(response);
    }


    @Operation(description = "Operação para colocar ingressos do setor leste superior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingressos criados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/insert-upper-east")
    public ResponseEntity<List<Ticket>> insertTicketUpperEast(@RequestBody @Valid CreateTicketDTO request) {
        var response = ticketManagementService.insertTicketUpperEast(request);
        return ResponseEntity.ok().body(response);
    }

    @Operation(description = "Operação para colocar ingressos do setor oeste inferior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingressos criados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/insert-lower-west")
    public ResponseEntity<List<Ticket>> insertTicketLowerWest(@RequestBody @Valid CreateTicketDTO request) {
        var response = ticketManagementService.insertTicketLowerWest(request);
        return ResponseEntity.ok().body(response);
    }


    @Operation(description = "Operação para colocar ingressos do setor oeste superior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingressos criados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/insert-upper-west")
    public ResponseEntity<List<Ticket>> insertTicketUpperWest(@RequestBody @Valid CreateTicketDTO request) {
        var response = ticketManagementService.insertTicketUpperWest(request);
        return ResponseEntity.ok().body(response);
    }


    @Operation(description = "Operação para colocar ingressos do setor sul inferior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingressos criados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/insert-lower-south")
    public ResponseEntity<List<Ticket>> insertTicketLowerSouth(@RequestBody @Valid CreateTicketDTO request) {
        var response = ticketManagementService.insertTicketLowerSouth(request);
        return ResponseEntity.ok().body(response);
    }


    @Operation(description = "Operação para colocar ingressos do setor sul superior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingressos criados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/insert-upper-south")
    public ResponseEntity<List<Ticket>> insertTicketUpperSouth(@RequestBody @Valid CreateTicketDTO request) {
        var response = ticketManagementService.insertTicketUpperSouth(request);
        return ResponseEntity.ok().body(response);
    }


    @Operation(description = "Operação para colocar ingressos do setor norte inferior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingressos criados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/insert-lower-north")
    public ResponseEntity<List<Ticket>> insertTicketLowerNorth(@RequestBody @Valid CreateTicketDTO request) {
        var response = ticketManagementService.insertTicketLowerNorth(request);
        return ResponseEntity.ok().body(response);
    }


    @Operation(description = "Operação para colocar ingressos do setor norte superior a venda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ingressos criados com sucesso"),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/insert-upper-north")
    public ResponseEntity<List<Ticket>> insertTicketUpperNorth(@RequestBody @Valid CreateTicketDTO request) {
        var response = ticketManagementService.insertTicketUpperNorth(request);
        return ResponseEntity.ok().body(response);
    }

}
