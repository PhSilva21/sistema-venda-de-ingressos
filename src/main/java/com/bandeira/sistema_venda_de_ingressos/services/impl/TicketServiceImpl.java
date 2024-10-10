package com.bandeira.sistema_venda_de_ingressos.services.impl;


import com.bandeira.sistema_venda_de_ingressos.dtos.BuyTicketDTO;
import com.bandeira.sistema_venda_de_ingressos.dtos.CreateTicketDTO;
import com.bandeira.sistema_venda_de_ingressos.exceptions.TicketsSoldOut;
import com.bandeira.sistema_venda_de_ingressos.exceptions.UserNotFoundException;
import com.bandeira.sistema_venda_de_ingressos.models.Ticket;
import com.bandeira.sistema_venda_de_ingressos.models.enums.StatusTicket;
import com.bandeira.sistema_venda_de_ingressos.repositories.TicketRepository;
import com.bandeira.sistema_venda_de_ingressos.repositories.UserRepository;
import com.bandeira.sistema_venda_de_ingressos.services.EmailService;
import com.bandeira.sistema_venda_de_ingressos.services.TicketService;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    private final UserRepository userRepository;

    private final EmailService emailService;

    @PersistenceContext
    private final EntityManager entityManager;

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
    public Ticket filterList() {
        var filterList = ticketRepository.findAll().stream().filter(t
                -> t.getStatusTicket().equals(StatusTicket.AVAILABLE)).toList().get(0);

        if(filterList == null){
            throw new TicketsSoldOut();
        }

        return filterList;
    }

    @Override
    public void deleteAllTickets() {
        ticketRepository.deleteAll();
    }

    @Override
    public void insertTicket(CreateTicketDTO request) {
        for (int i = 0; i <= request.numberOfTickets(); i++) {

            long chair = i + 1L;

            Ticket ticket = new Ticket();

            ticket.setPrice(request.price());
            ticket.setChair(chair);
            ticket.setStatusTicket(StatusTicket.AVAILABLE);

            List<Ticket> tickets = new ArrayList<>();

            tickets.add(ticket);

            ticketRepository.saveAll(tickets);

        }
    }

}
