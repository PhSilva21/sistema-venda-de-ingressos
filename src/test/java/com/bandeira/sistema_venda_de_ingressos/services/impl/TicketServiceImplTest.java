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
import org.junit.jupiter.api.DisplayName;
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
        @DisplayName("Buy tickets successfully")
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
        @DisplayName("Handle error when user is not found")
        void handleErrorWhenUserIsNotFound() throws MessagingException, UnsupportedEncodingException {
            doThrow(new UserNotFoundException())
                    .when(userService)
                    .findById(buyTicketDTO.userId());


            assertThrows(UserNotFoundException.class,
                    () -> ticketService.buyTicket(buyTicketDTO));
        }

        @Test
        @DisplayName("Should make an exception when the tickets are sold out")
        void ShouldMakeExceptionWhenTheTicketsAreSoldOut() {
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
        @DisplayName("Filter tickets in the Lower East sector")
        void filterLowerEast() {
            var ticketList = List.of(new Ticket(BigDecimal.valueOf(127.00), 1L, LocalDate.now()
                            , SectorTicket.LOWER_EAST, StatusTicket.AVAILABLE));
            doReturn(ticketList).when(ticketRepository).findAll();

            var response = ticketService.filterLowerEast();

            assertEquals(response.size(), ticketList.size());
            assertEquals(response.get(0).getSector(), SectorTicket.LOWER_EAST);
            assertEquals(response.get(0).getStatusTicket(), StatusTicket.AVAILABLE);
        }

        @Test
        @DisplayName("Filter tickets in the Upper East sector")
        void filterUpperEast() {
            var ticketList = List.of(new Ticket(BigDecimal.valueOf(127.00), 1L, LocalDate.now()
                    , SectorTicket.UPPER_EAST, StatusTicket.AVAILABLE));
            doReturn(ticketList).when(ticketRepository).findAll();

            var response = ticketService.filterUppersEast();

            assertEquals(response.size(), ticketList.size());
            assertEquals(response.get(0).getSector(), SectorTicket.UPPER_EAST);
            assertEquals(response.get(0).getStatusTicket(), StatusTicket.AVAILABLE);
        }

        @Test
        @DisplayName("Filter tickets in the Lower West sector")
        void filterLowerWest() {
            var ticketList = List.of(new Ticket(BigDecimal.valueOf(100.00), 3L, LocalDate.now(),
                    SectorTicket.LOWER_WEST, StatusTicket.AVAILABLE));
            doReturn(ticketList).when(ticketRepository).findAll();

            var response = ticketService.filterLowerWest();

            assertEquals(response.size(), ticketList.size());
            assertEquals(response.get(0).getSector(), SectorTicket.LOWER_WEST);
            assertEquals(response.get(0).getStatusTicket(), StatusTicket.AVAILABLE);
        }

        @Test
        @DisplayName("Filter tickets in the Upper West sector")
        void filterUpperWest() {
            var ticketList = List.of(new Ticket(BigDecimal.valueOf(200.00), 4L, LocalDate.now(),
                    SectorTicket.UPPER_WEST, StatusTicket.AVAILABLE));
            doReturn(ticketList).when(ticketRepository).findAll();

            var response = ticketService.filterUpperWest();

            assertEquals(response.size(), ticketList.size());
            assertEquals(response.get(0).getSector(), SectorTicket.UPPER_WEST);
            assertEquals(response.get(0).getStatusTicket(), StatusTicket.AVAILABLE);
        }

        @Test
        @DisplayName("Filter tickets in the Lower South sector")
        void filterLowerSouth() {
            var ticketList = List.of(new Ticket(BigDecimal.valueOf(80.00), 5L, LocalDate.now(),
                    SectorTicket.LOWER_SOUTH, StatusTicket.AVAILABLE));
            doReturn(ticketList).when(ticketRepository).findAll();

            var response = ticketService.filterLowerSouth();

            assertEquals(response.size(), ticketList.size());
            assertEquals(response.get(0).getSector(), SectorTicket.LOWER_SOUTH);
            assertEquals(response.get(0).getStatusTicket(), StatusTicket.AVAILABLE);
        }

        @Test
        @DisplayName("Filter tickets in the Upper South sector")
        void filterUpperSouth() {
            var ticketList = List.of(new Ticket(BigDecimal.valueOf(90.00), 6L, LocalDate.now(),
                    SectorTicket.UPPER_SOUTH, StatusTicket.AVAILABLE));
            doReturn(ticketList).when(ticketRepository).findAll();

            var response = ticketService.filterUpperSouth();

            assertEquals(response.size(), ticketList.size());
            assertEquals(response.get(0).getSector(), SectorTicket.UPPER_SOUTH);
            assertEquals(response.get(0).getStatusTicket(), StatusTicket.AVAILABLE);
        }

        @Test
        @DisplayName("Filter tickets in the Lower North sector")
        void filterLowerNorth() {
            var ticketList = List.of(new Ticket(BigDecimal.valueOf(75.00), 7L, LocalDate.now(),
                    SectorTicket.LOWER_NORTH, StatusTicket.AVAILABLE));
            doReturn(ticketList).when(ticketRepository).findAll();

            var response = ticketService.filterLowerNorth();

            assertEquals(response.size(), ticketList.size());
            assertEquals(response.get(0).getSector(), SectorTicket.LOWER_NORTH);
            assertEquals(response.get(0).getStatusTicket(), StatusTicket.AVAILABLE);
        }

        @Test
        @DisplayName("Filter tickets in the Upper North sector")
        void filterUpperNorth() {
            var ticketList = List.of(new Ticket(BigDecimal.valueOf(110.00), 8L, LocalDate.now(),
                    SectorTicket.UPPER_NORTH, StatusTicket.AVAILABLE));
            doReturn(ticketList).when(ticketRepository).findAll();

            var response = ticketService.filterUpperNorth();

            assertEquals(response.size(), ticketList.size());
            assertEquals(response.get(0).getSector(), SectorTicket.UPPER_NORTH);
            assertEquals(response.get(0).getStatusTicket(), StatusTicket.AVAILABLE);
        }
    }
}