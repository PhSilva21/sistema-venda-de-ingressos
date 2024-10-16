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
import com.bandeira.sistema_venda_de_ingressos.services.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    private final UserService userService;

    private final EmailService emailService;


    @Override
    public void buyTicket(BuyTicketDTO request) throws MessagingException, UnsupportedEncodingException {
        var user = userService.findById(request.userId());
        var ticket = findById(request.ticketId());

        ticket.setUser(user);

        ticket.setStatusTicket(StatusTicket.UNAVAILABLE);

        user.getTickets().add(ticket);

        emailService.sendEmailBuyTicket(user);

        ticketRepository.save(ticket);
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

    @Override
    public Ticket findById(Long id) {
        return ticketRepository.findById(id).orElseThrow(TicketsSoldOut::new);
    }





}
