package com.bandeira.sistema_venda_de_ingressos.services;

import com.bandeira.sistema_venda_de_ingressos.dtos.BuyTicketDTO;
import com.bandeira.sistema_venda_de_ingressos.dtos.CreateTicketDTO;
import com.bandeira.sistema_venda_de_ingressos.models.Ticket;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;
import java.util.List;


public interface TicketService {

    void buyTicket(BuyTicketDTO request) throws MessagingException, UnsupportedEncodingException;

    List<Ticket> filterLowerEast();

    List<Ticket> filterUppersEast();

    List<Ticket> filterLowerWest();

    List<Ticket> filterUpperWest();

    List<Ticket> filterLowerSouth();

    List<Ticket> filterUpperSouth();

    List<Ticket> filterLowerNorth();

    List<Ticket> filterUpperNorth();

    Ticket findById(Long id);


}
