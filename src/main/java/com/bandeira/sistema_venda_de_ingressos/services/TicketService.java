package com.bandeira.sistema_venda_de_ingressos.services;

import com.bandeira.sistema_venda_de_ingressos.dtos.BuyTicketDTO;
import com.bandeira.sistema_venda_de_ingressos.dtos.CreateTicketDTO;
import com.bandeira.sistema_venda_de_ingressos.models.Ticket;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;


public interface TicketService {

    void buyTicket(BuyTicketDTO request) throws MessagingException, UnsupportedEncodingException;

    void deleteAllTickets();

    void insertTicketLowerEast(CreateTicketDTO request);

    void insertTicketUpperEast(CreateTicketDTO request);

    void insertTicketLowerWest(CreateTicketDTO request);

    void insertTicketUpperWest(CreateTicketDTO request);

    void insertTicketLowerSouth(CreateTicketDTO request);

    void insertTicketUpperSouth(CreateTicketDTO request);

    void insertTicketLowerNorth(CreateTicketDTO request);

    void insertTicketUpperNorth(CreateTicketDTO request);

    List<Ticket> filterLowerEast();

    List<Ticket> filterUppersEast();

    List<Ticket> filterLowerWest();

    List<Ticket> filterUpperWest();

    List<Ticket> filterLowerSouth();

    List<Ticket> filterUpperSouth();

    List<Ticket> filterLowerNorth();

    List<Ticket> filterUpperNorth();


}
