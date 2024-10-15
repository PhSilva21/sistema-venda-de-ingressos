package com.bandeira.sistema_venda_de_ingressos.services;

import com.bandeira.sistema_venda_de_ingressos.dtos.CreateTicketDTO;
import com.bandeira.sistema_venda_de_ingressos.models.Ticket;

import java.util.List;

public interface TicketManagementService {


    List<Ticket> saveAllTickets(List<Ticket> tickets);

    List<Ticket> insertTicketLowerEast(CreateTicketDTO request);

    void insertTicketUpperEast(CreateTicketDTO request);

    void insertTicketLowerWest(CreateTicketDTO request);

    void insertTicketUpperWest(CreateTicketDTO request);

    void insertTicketLowerSouth(CreateTicketDTO request);

    void insertTicketUpperSouth(CreateTicketDTO request);

    void insertTicketLowerNorth(CreateTicketDTO request);

    void insertTicketUpperNorth(CreateTicketDTO request);

    void deleteAllTickets();
}
