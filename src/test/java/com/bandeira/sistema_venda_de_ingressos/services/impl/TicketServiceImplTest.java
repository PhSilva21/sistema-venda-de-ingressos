package com.bandeira.sistema_venda_de_ingressos.services.impl;

import com.bandeira.sistema_venda_de_ingressos.dtos.BuyTicketDTO;
import com.bandeira.sistema_venda_de_ingressos.dtos.CreateTicketDTO;
import com.bandeira.sistema_venda_de_ingressos.exceptions.TicketsSoldOut;
import com.bandeira.sistema_venda_de_ingressos.exceptions.UserNotFoundException;
import com.bandeira.sistema_venda_de_ingressos.models.Ticket;
import com.bandeira.sistema_venda_de_ingressos.models.User;
import com.bandeira.sistema_venda_de_ingressos.models.enums.SectorTicket;
import com.bandeira.sistema_venda_de_ingressos.models.enums.StatusTicket;
import com.bandeira.sistema_venda_de_ingressos.models.enums.UserRole;
import com.bandeira.sistema_venda_de_ingressos.repositories.TicketRepository;
import com.bandeira.sistema_venda_de_ingressos.repositories.UserRepository;
import com.bandeira.sistema_venda_de_ingressos.services.EmailService;
import com.bandeira.sistema_venda_de_ingressos.services.TicketService;
import com.bandeira.sistema_venda_de_ingressos.services.UserService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {

    @InjectMocks
    TicketServiceImpl ticketService;

    @Mock
    TicketRepository ticketRepository;

    @Mock
    UserService userService;

    @Mock
    EmailService emailService;

    @Captor
    ArgumentCaptor<Ticket> ticketArgumentCaptor;

    @Nested
    class buyTicket {
        Ticket ticket = new Ticket(1L, BigDecimal.valueOf(175.90), 1L, LocalDate.now()
                , SectorTicket.LOWER_EAST, StatusTicket.AVAILABLE);

        User user = new User(1L, "Lucas Walter", "marcos@gmail.com", "marcos33"
                , "813663617351", UserRole.USER
        );

        BuyTicketDTO buyTicketDTO = new BuyTicketDTO(ticket.getId(), user.getId());

        @Test
        void buyTickets() throws MessagingException, UnsupportedEncodingException {
            var ticketList = List.of(ticket);

            doReturn(user)
                    .when(userService)
                    .findById(buyTicketDTO.userId());
            doReturn(Optional.of(ticket))
                    .when(ticketRepository).findById(buyTicketDTO.ticketId());
            doNothing()
                    .when(emailService)
                    .sendEmailBuyTicket(user);
            doReturn(ticket)
                    .when(ticketRepository)
                    .save(ticketArgumentCaptor.capture());

            ticketService.buyTicket(buyTicketDTO);

            var ticketCaptured = ticketArgumentCaptor.getValue();

            assertNotNull(ticket);
            assertEquals(ticket.getUser(), user);
            assertEquals(ticket.getStatusTicket(), StatusTicket.UNAVAILABLE);
        }

        @Test
        void erroUser() throws MessagingException, UnsupportedEncodingException {
            doThrow(new UserNotFoundException())
                    .when(userService)
                    .findById(buyTicketDTO.userId());


            assertThrows(UserNotFoundException.class,
                    () -> ticketService.buyTicket(buyTicketDTO));
        }

        @Test
        void erroTicket() {
            doReturn(user)
                    .when(userService)
                    .findById(buyTicketDTO.userId());
            doReturn(Optional.empty())
                    .when(ticketRepository)
                    .findById(buyTicketDTO.ticketId());

            assertThrows(TicketsSoldOut.class,
                    () -> ticketService.buyTicket(buyTicketDTO));
        }
    }

    @Nested
    class FilterBySector {


        @Test
        void insertTicketLowerNorth() {
        }

        @Test
        void insertTicketUpperNorth() {
        }

        @Test
        void filterLowerEast() {

        }

        @Test
        void filterUppersEast() {
        }

        @Test
        void filterLowerWest() {
        }

        @Test
        void filterUpperWest() {
        }

        @Test
        void filterLowerSouth() {
        }

        @Test
        void filterUpperSouth() {
        }

        @Test
        void filterLowerNorth() {
        }

        @Test
        void filterUpperNorth() {
        }

        @Test
        void filterList() {
        }

        @Test
        void deleteAllTickets() {
        }
    }
}