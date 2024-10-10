package com.bandeira.sistema_venda_de_ingressos.services;

import com.bandeira.sistema_venda_de_ingressos.dtos.BuyTicketDTO;
import com.bandeira.sistema_venda_de_ingressos.dtos.CreateTicketDTO;
import com.bandeira.sistema_venda_de_ingressos.models.Ticket;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;

import java.io.UnsupportedEncodingException;


public interface TicketService {

    void buyTicket(BuyTicketDTO request) throws MessagingException, UnsupportedEncodingException;

    Ticket filterList();

    void deleteAllTickets();

    @Transactional
    void insertTicket(CreateTicketDTO request);

    void filterLowerEastTickets();

}
