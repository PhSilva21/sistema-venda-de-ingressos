package com.bandeira.sistema_venda_de_ingressos.services.impl;


import com.bandeira.sistema_venda_de_ingressos.dtos.BuyTicketDTO;
import com.bandeira.sistema_venda_de_ingressos.dtos.CreateTicketDTO;
import com.bandeira.sistema_venda_de_ingressos.exceptions.TicketsSoldOut;
import com.bandeira.sistema_venda_de_ingressos.exceptions.UserNotFoundException;
import com.bandeira.sistema_venda_de_ingressos.models.Ticket;
import com.bandeira.sistema_venda_de_ingressos.models.enums.SectorTicket;
import com.bandeira.sistema_venda_de_ingressos.models.enums.StatusTicket;
import com.bandeira.sistema_venda_de_ingressos.repositories.TicketRepository;
import com.bandeira.sistema_venda_de_ingressos.repositories.UserRepository;
import com.bandeira.sistema_venda_de_ingressos.services.EmailService;
import com.bandeira.sistema_venda_de_ingressos.services.TicketService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    private final UserRepository userRepository;

    private final EmailService emailService;


    @Override
    public void buyTicket(BuyTicketDTO request) throws MessagingException, UnsupportedEncodingException {

        var ticket = filterList();

        var user = userRepository.findById(request.userId())
                .orElseThrow(UserNotFoundException::new);

        ticket.setUser(user);

        ticket.setStatusTicket(StatusTicket.UNAVAILABLE);

        emailService.sendEmailBuyTicket(user);

        ticketRepository.save(ticket);
    }



    @Override
    public void insertTicketLowerEast(CreateTicketDTO request) {
        for (long i = 1; i <= request.numberOfTickets(); i++) {


            Ticket ticket = new Ticket(
                    request.price(),
                    i,
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

            List<Ticket> tickets = new ArrayList<>();

            tickets.add(ticket);

            ticketRepository.saveAll(tickets);

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
    public List<Ticket> filterLowerEast() {
        return ticketRepository.findAll().stream().filter(t -> t.getStatusTicket()
                        .equals(StatusTicket.AVAILABLE) && t.getSector()
                        .equals(SectorTicket.LOWER_EAST))
                .toList();
    }

    @Override
    public List<Ticket> filterUppersEast() {
        return ticketRepository.findAll().stream().filter(t -> t.getStatusTicket()
                        .equals(StatusTicket.AVAILABLE) && t.getSector()
                        .equals(SectorTicket.UPPER_EAST))
                .toList();
    }

    @Override
    public List<Ticket> filterLowerWest() {
        return ticketRepository.findAll().stream().filter(t -> t.getStatusTicket()
                        .equals(StatusTicket.AVAILABLE) && t.getSector()
                        .equals(SectorTicket.LOWER_WEST))
                .toList();
    }

    @Override
    public List<Ticket> filterUpperWest() {
        return ticketRepository.findAll().stream().filter(t -> t.getStatusTicket()
                        .equals(StatusTicket.AVAILABLE) && t.getSector()
                        .equals(SectorTicket.UPPER_WEST))
                .toList();
    }

    @Override
    public List<Ticket> filterLowerSouth() {
        return ticketRepository.findAll().stream().filter(t -> t.getStatusTicket()
                        .equals(StatusTicket.AVAILABLE) && t.getSector().
                        equals(SectorTicket.LOWER_SOUTH))
                .toList();
    }

    @Override
    public List<Ticket> filterUpperSouth() {
        return ticketRepository.findAll().stream().filter(t -> t.getStatusTicket()
                        .equals(StatusTicket.AVAILABLE) && t.getSector()
                        .equals(SectorTicket.UPPER_SOUTH))
                .toList();
    }

    @Override
    public List<Ticket> filterLowerNorth() {
        return ticketRepository.findAll().stream().filter(t -> t.getStatusTicket()
                        .equals(StatusTicket.AVAILABLE) && t.getSector()
                        .equals(SectorTicket.LOWER_NORTH))
                .toList();
    }

    @Override
    public List<Ticket> filterUpperNorth() {
        return ticketRepository.findAll().stream().filter(t -> t.getStatusTicket()
                        .equals(StatusTicket.AVAILABLE) && t.getSector()
                        .equals(SectorTicket.UPPER_NORTH))
                .toList();
    }

    public Ticket filterList(){
        var ticket = ticketRepository.findAll().stream().filter(t -> t.getStatusTicket()
                .equals(StatusTicket.AVAILABLE)).toList().get(0);

        if(ticket == null){
            throw new TicketsSoldOut();
        }

        return ticket;
    }

    @Override
    public void deleteAllTickets() {
        ticketRepository.deleteAll();
    }

    @Override
    public void insertTicket(CreateTicketDTO request) {

    }

}
