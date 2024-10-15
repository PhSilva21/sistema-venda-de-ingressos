package com.bandeira.sistema_venda_de_ingressos.services;

import com.bandeira.sistema_venda_de_ingressos.dtos.CreateTicketDTO;
import com.bandeira.sistema_venda_de_ingressos.models.Ticket;

import java.util.List;

public interface TicketManagementService {


    List<Ticket> saveAllTickets(List<Ticket> tickets);

    List<Ticket> insertTicketLowerEast(CreateTicketDTO request);

    List<Ticket> insertTicketUpperEast(CreateTicketDTO request);

    List<Ticket> insertTicketLowerWest(CreateTicketDTO request);

    List<Ticket> insertTicketUpperWest(CreateTicketDTO request);

    List<Ticket> insertTicketLowerSouth(CreateTicketDTO request);

    List<Ticket> insertTicketUpperSouth(CreateTicketDTO request);

    List<Ticket> insertTicketLowerNorth(CreateTicketDTO request);

    List<Ticket> insertTicketUpperNorth(CreateTicketDTO request);

    void deleteAllTickets();
}
