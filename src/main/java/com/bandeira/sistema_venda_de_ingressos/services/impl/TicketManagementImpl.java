package com.bandeira.sistema_venda_de_ingressos.services.impl;

import com.bandeira.sistema_venda_de_ingressos.dtos.CreateTicketDTO;
import com.bandeira.sistema_venda_de_ingressos.exceptions.NumberOfTicketsLargerThanTheSectorException;
import com.bandeira.sistema_venda_de_ingressos.models.Ticket;
import com.bandeira.sistema_venda_de_ingressos.models.enums.SectorTicket;
import com.bandeira.sistema_venda_de_ingressos.models.enums.StatusTicket;
import com.bandeira.sistema_venda_de_ingressos.repositories.TicketRepository;
import com.bandeira.sistema_venda_de_ingressos.services.TicketManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketManagementImpl implements TicketManagementService {

    private final TicketRepository ticketRepository;


    @Override
    public List<Ticket> saveAllTickets(List<Ticket> tickets){
        return ticketRepository.saveAll(tickets).subList(0,3);
    }

    @Override
    public List<Ticket> insertTicketLowerEast(CreateTicketDTO request) {
        if(request.numberOfTickets() > 5625){
            throw new NumberOfTicketsLargerThanTheSectorException();
        }

        List<Ticket> tickets = new ArrayList<>();
        for (long i = 1; i <= request.numberOfTickets(); i++) {

            Ticket ticket = new Ticket(
                    request.price(),
                    i,
                    request.date(),
                    SectorTicket.LOWER_EAST,
                    StatusTicket.AVAILABLE
            );
            tickets.add(ticket);
        }
        return saveAllTickets(tickets);
    }

    @Override
    public List<Ticket> insertTicketUpperEast(CreateTicketDTO request) {
        if(request.numberOfTickets() > 5625){
            throw new NumberOfTicketsLargerThanTheSectorException();
        }

        List<Ticket> tickets = new ArrayList<>();
        for (long i = 1; i <= request.numberOfTickets(); i++) {

            long chair = 5625 + i;

            Ticket ticket = new Ticket(
                    request.price(),
                    chair,
                    request.date(),
                    SectorTicket.UPPER_EAST,
                    StatusTicket.AVAILABLE
            );
            tickets.add(ticket);
        }
        return saveAllTickets(tickets);
    }

    @Override
    public List<Ticket> insertTicketLowerWest(CreateTicketDTO request) {
        if(request.numberOfTickets() > 5625){
            throw new NumberOfTicketsLargerThanTheSectorException();
        }

        List<Ticket> tickets = new ArrayList<>();
        for (long i = 1; i <= request.numberOfTickets(); i++) {

            long chair = 11250 + i;

            Ticket ticket = new Ticket(
                    request.price(),
                    chair,
                    request.date(),
                    SectorTicket.LOWER_WEST,
                    StatusTicket.AVAILABLE
            );

            tickets.add(ticket);
        }
        return saveAllTickets(tickets);
    }

    @Override
    public List<Ticket> insertTicketUpperWest(CreateTicketDTO request) {
        if(request.numberOfTickets() > 5625){
            throw new NumberOfTicketsLargerThanTheSectorException();
        }

        List<Ticket> tickets = new ArrayList<>();
        for (long i = 1; i <= request.numberOfTickets(); i++) {

            long chair = 16875 + i;

            Ticket ticket = new Ticket(
                    request.price(),
                    chair,
                    request.date(),
                    SectorTicket.UPPER_WEST,
                    StatusTicket.AVAILABLE
            );
            tickets.add(ticket);
        }
        return saveAllTickets(tickets);
    }

    @Override
    public List<Ticket> insertTicketLowerSouth(CreateTicketDTO request) {
        if(request.numberOfTickets() > 5625){
            throw new NumberOfTicketsLargerThanTheSectorException();
        }

        List<Ticket> tickets = new ArrayList<>();
        for (long i = 1; i <= request.numberOfTickets(); i++) {

            long chair = 22500 + i;

            Ticket ticket = new Ticket(
                    request.price(),
                    chair,
                    request.date(),
                    SectorTicket.LOWER_SOUTH,
                    StatusTicket.AVAILABLE
            );
            tickets.add(ticket);
        }
        return saveAllTickets(tickets);
    }

    @Override
    public List<Ticket> insertTicketUpperSouth(CreateTicketDTO request) {
        if(request.numberOfTickets() > 5625){
            throw new NumberOfTicketsLargerThanTheSectorException();
        }

        List<Ticket> tickets = new ArrayList<>();
        for (long i = 1; i <= request.numberOfTickets(); i++) {

            long chair = 28125 + i;

            Ticket ticket = new Ticket(
                    request.price(),
                    chair,
                    request.date(),
                    SectorTicket.UPPER_SOUTH,
                    StatusTicket.AVAILABLE
            );
            tickets.add(ticket);
        }
        return saveAllTickets(tickets);
    }

    @Override
    public List<Ticket> insertTicketLowerNorth(CreateTicketDTO request) {
        if(request.numberOfTickets() > 5625){
            throw new NumberOfTicketsLargerThanTheSectorException();
        }

        List<Ticket> tickets = new ArrayList<>();
        for (long i = 1; i <= request.numberOfTickets(); i++) {

            long chair = 33750 + i;

            Ticket ticket = new Ticket(
                    request.price(),
                    chair,
                    request.date(),
                    SectorTicket.LOWER_NORTH,
                    StatusTicket.AVAILABLE
            );
            tickets.add(ticket);
        }
        return saveAllTickets(tickets);
    }

    @Override
    public List<Ticket> insertTicketUpperNorth(CreateTicketDTO request) {
        if(request.numberOfTickets() > 5625){
            throw new NumberOfTicketsLargerThanTheSectorException();
        }

        List<Ticket> tickets = new ArrayList<>();
        for (long i = 1; i <= request.numberOfTickets(); i++) {

            long chair = 39375 + i;

            Ticket ticket = new Ticket(
                    request.price(),
                    chair,
                    request.date(),
                    SectorTicket.UPPER_SOUTH,
                    StatusTicket.AVAILABLE
            );
            tickets.add(ticket);
        }
        return saveAllTickets(tickets);
    }

    @Override
    public void deleteAllTickets() {
        ticketRepository.deleteAll();
    }
}
