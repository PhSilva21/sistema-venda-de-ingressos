package com.bandeira.sistema_venda_de_ingressos.services.impl;

import com.bandeira.sistema_venda_de_ingressos.dtos.CreateTicketDTO;
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
    public void insertTicketUpperEast(CreateTicketDTO request) {
        for (long i = 1; i <= request.numberOfTickets(); i++) {

            long chair = 5625 + i;

            Ticket ticket = new Ticket(
                    request.price(),
                    chair,
                    request.date(),
                    SectorTicket.LOWER_EAST,
                    StatusTicket.AVAILABLE
            );

            List<Ticket> tickets = new ArrayList<>();

            tickets.add(ticket);

            ticketRepository.saveAll(tickets);

        }
    }

    @Override
    public void insertTicketLowerWest(CreateTicketDTO request) {
        for (long i = 1; i <= request.numberOfTickets(); i++) {

            long chair = 11250 + i;

            Ticket ticket = new Ticket(
                    request.price(),
                    chair,
                    request.date(),
                    SectorTicket.LOWER_EAST,
                    StatusTicket.AVAILABLE
            );

            List<Ticket> tickets = new ArrayList<>();

            tickets.add(ticket);

            ticketRepository.saveAll(tickets);

        }
    }

    @Override
    public void insertTicketUpperWest(CreateTicketDTO request) {
        for (long i = 1; i <= request.numberOfTickets(); i++) {

            long chair = 16875 + i;

            Ticket ticket = new Ticket(
                    request.price(),
                    chair,
                    request.date(),
                    SectorTicket.LOWER_EAST,
                    StatusTicket.AVAILABLE
            );

            List<Ticket> tickets = new ArrayList<>();

            tickets.add(ticket);

            ticketRepository.saveAll(tickets);

        }
    }

    @Override
    public void insertTicketLowerSouth(CreateTicketDTO request) {
        for (long i = 1; i <= request.numberOfTickets(); i++) {

            long chair = 22500 + i;

            Ticket ticket = new Ticket(
                    request.price(),
                    chair,
                    request.date(),
                    SectorTicket.LOWER_EAST,
                    StatusTicket.AVAILABLE
            );

            ticketRepository.save(ticket);
        }

    }

    @Override
    public void insertTicketUpperSouth(CreateTicketDTO request) {
        for (long i = 1; i <= request.numberOfTickets(); i++) {

            long chair = 28125 + i;

            Ticket ticket = new Ticket(
                    request.price(),
                    chair,
                    request.date(),
                    SectorTicket.LOWER_EAST,
                    StatusTicket.AVAILABLE
            );

            List<Ticket> tickets = new ArrayList<>();

            tickets.add(ticket);

            ticketRepository.saveAll(tickets);

        }
    }

    @Override
    public void insertTicketLowerNorth(CreateTicketDTO request) {
        for (long i = 1; i <= request.numberOfTickets(); i++) {

            long chair = 33750 + i;

            Ticket ticket = new Ticket(
                    request.price(),
                    chair,
                    request.date(),
                    SectorTicket.LOWER_EAST,
                    StatusTicket.AVAILABLE
            );

            List<Ticket> tickets = new ArrayList<>();

            tickets.add(ticket);

            ticketRepository.saveAll(tickets);

        }
    }

    @Override
    public void insertTicketUpperNorth(CreateTicketDTO request) {
        for (long i = 1; i <= request.numberOfTickets(); i++) {

            long chair = 39375 + i;

            Ticket ticket = new Ticket(
                    request.price(),
                    chair,
                    request.date(),
                    SectorTicket.LOWER_EAST,
                    StatusTicket.AVAILABLE
            );

            List<Ticket> tickets = new ArrayList<>();

            tickets.add(ticket);

            ticketRepository.saveAll(tickets);

        }
    }

    @Override
    public void deleteAllTickets() {
        ticketRepository.deleteAll();
    }
}
