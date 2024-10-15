package com.bandeira.sistema_venda_de_ingressos.services.impl;

import com.bandeira.sistema_venda_de_ingressos.dtos.CreateTicketDTO;
import com.bandeira.sistema_venda_de_ingressos.exceptions.NumberOfTicketsLargerThanTheSectorException;
import com.bandeira.sistema_venda_de_ingressos.models.Ticket;
import com.bandeira.sistema_venda_de_ingressos.models.enums.SectorTicket;
import com.bandeira.sistema_venda_de_ingressos.models.enums.StatusTicket;
import com.bandeira.sistema_venda_de_ingressos.repositories.TicketRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketManagementImplTest {

    @InjectMocks
    TicketManagementImpl ticketManagement;

    @Mock
    TicketRepository ticketRepository;

    @Nested
    class SaveAllTickets {
        @Test
        @DisplayName("Save all tickets successfully")
        void saveAllTickets() {
            var ticketList = List.of(new Ticket(BigDecimal.valueOf(127.00), 4L, LocalDate.now()
                    , SectorTicket.LOWER_EAST, StatusTicket.AVAILABLE), new Ticket(BigDecimal.valueOf(127.00), 4L, LocalDate.now()
                    , SectorTicket.LOWER_EAST, StatusTicket.AVAILABLE), new Ticket(BigDecimal.valueOf(127.00), 4L, LocalDate.now()
                    , SectorTicket.LOWER_EAST, StatusTicket.AVAILABLE));

            doReturn(ticketList).when(ticketRepository).saveAll(any());

            var response = ticketManagement.saveAllTickets(ticketList);

            assertFalse(response.isEmpty());
            assertEquals(response.size(), ticketList.size());
        }
    }

    @Nested
    class insertTicketBySector {

        List<Ticket> ticketList = List.of(new Ticket(BigDecimal.valueOf(127.00), 1L, LocalDate.now()
                , SectorTicket.LOWER_EAST, StatusTicket.AVAILABLE), new Ticket(BigDecimal.valueOf(127.00)
                , 2L, LocalDate.now()
                , SectorTicket.LOWER_EAST, StatusTicket.AVAILABLE), new Ticket(BigDecimal.valueOf(127.00)
                , 3L, LocalDate.now()
                , SectorTicket.LOWER_EAST, StatusTicket.AVAILABLE));

        CreateTicketDTO createTicketDTO = new CreateTicketDTO(BigDecimal.valueOf(176.90), 3
                , LocalDate.of(2024,11,23));

        CreateTicketDTO errorCreateExample = new CreateTicketDTO(BigDecimal.valueOf(176.90), 7000
                , LocalDate.of(2024,11,23));

        @Test
        @DisplayName("Insert ticket in the lower east sector successfully")
        void insertTicketLowerEast() {
            doReturn(ticketList).when(ticketRepository).saveAll(any());

            var response = ticketManagement.insertTicketLowerEast(createTicketDTO);

            assertEquals(response.size(), createTicketDTO.numberOfTickets());
            verify(ticketRepository, times(1)).saveAll(any());

            assertEquals(response.get(0).getChair(), ticketList.get(0).getChair());
            assertEquals(response.get(0).getPrice(), ticketList.get(0).getPrice());
            assertEquals(response.get(0).getSector(), ticketList.get(0).getSector());
            assertEquals(response.get(0).getStatusTicket(), ticketList.get(0).getStatusTicket());
            assertEquals(response.get(1).getChair(), ticketList.get(1).getChair());
            assertEquals(response.get(1).getPrice(), ticketList.get(1).getPrice());
            assertEquals(response.get(1).getSector(), ticketList.get(1).getSector());
            assertEquals(response.get(1).getStatusTicket(), ticketList.get(1).getStatusTicket());
            assertEquals(response.get(2).getChair(), ticketList.get(2).getChair());
            assertEquals(response.get(2).getPrice(), ticketList.get(2).getPrice());
            assertEquals(response.get(2).getSector(), ticketList.get(2).getSector());
            assertEquals(response.get(2).getStatusTicket(), ticketList.get(2).getStatusTicket());
        }


        @Test
        void insertTicketUpperEast() {
            doReturn(ticketList).when(ticketRepository).saveAll(any());

            ticketList.get(0).setChair(ticketList.get(0).getChair() + 5625);
            ticketList.get(1).setChair(ticketList.get(1).getChair() + 5625);
            ticketList.get(2).setChair(ticketList.get(2).getChair() + 5625);

            var response = ticketManagement.insertTicketUpperEast(createTicketDTO);

            assertEquals(response.size(), createTicketDTO.numberOfTickets());
            verify(ticketRepository, times(1)).saveAll(any());

            assertEquals(response.get(0).getChair(), ticketList.get(0).getChair()) ;
            assertEquals(response.get(0).getPrice(), ticketList.get(0).getPrice());
            assertEquals(response.get(0).getSector(), ticketList.get(0).getSector());
            assertEquals(response.get(0).getStatusTicket(), ticketList.get(0).getStatusTicket());

            assertEquals(response.get(1).getChair(), ticketList.get(1).getChair());
            assertEquals(response.get(1).getPrice(), ticketList.get(1).getPrice());
            assertEquals(response.get(1).getSector(), ticketList.get(1).getSector());
            assertEquals(response.get(1).getStatusTicket(), ticketList.get(1).getStatusTicket());

            assertEquals(response.get(2).getChair(), ticketList.get(2).getChair());
            assertEquals(response.get(2).getPrice(), ticketList.get(2).getPrice());
            assertEquals(response.get(2).getSector(), ticketList.get(2).getSector());
            assertEquals(response.get(2).getStatusTicket(), ticketList.get(2).getStatusTicket());
        }

        @Test
        void insertTicketLowerWest() {
            doReturn(ticketList).when(ticketRepository).saveAll(any());

            ticketList.get(0).setChair(ticketList.get(0).getChair() + 11250);
            ticketList.get(1).setChair(ticketList.get(1).getChair() + 11250);
            ticketList.get(2).setChair(ticketList.get(2).getChair() + 11250);

            var response = ticketManagement.insertTicketLowerWest(createTicketDTO);

            assertEquals(response.size(), createTicketDTO.numberOfTickets());
            verify(ticketRepository, times(1)).saveAll(any());

            assertEquals(response.get(0).getChair(), ticketList.get(0).getChair());
            assertEquals(response.get(0).getPrice(), ticketList.get(0).getPrice());
            assertEquals(response.get(0).getSector(), ticketList.get(0).getSector());
            assertEquals(response.get(0).getStatusTicket(), ticketList.get(0).getStatusTicket());

            assertEquals(response.get(1).getChair(), ticketList.get(1).getChair());
            assertEquals(response.get(1).getPrice(), ticketList.get(1).getPrice());
            assertEquals(response.get(1).getSector(), ticketList.get(1).getSector());
            assertEquals(response.get(1).getStatusTicket(), ticketList.get(1).getStatusTicket());

            assertEquals(response.get(2).getChair(), ticketList.get(2).getChair());
            assertEquals(response.get(2).getPrice(), ticketList.get(2).getPrice());
            assertEquals(response.get(2).getSector(), ticketList.get(2).getSector());
            assertEquals(response.get(2).getStatusTicket(), ticketList.get(2).getStatusTicket());
        }

        @Test
        void insertTicketUpperWest() {
            doReturn(ticketList).when(ticketRepository).saveAll(any());

            ticketList.get(0).setChair(ticketList.get(0).getChair() + 16875);
            ticketList.get(1).setChair(ticketList.get(1).getChair() + 16875);
            ticketList.get(2).setChair(ticketList.get(2).getChair() + 16875);

            var response = ticketManagement.insertTicketUpperWest(createTicketDTO);

            assertEquals(response.size(), createTicketDTO.numberOfTickets());
            verify(ticketRepository, times(1)).saveAll(any());

            assertEquals(response.get(0).getChair(), ticketList.get(0).getChair());
            assertEquals(response.get(0).getPrice(), ticketList.get(0).getPrice());
            assertEquals(response.get(0).getSector(), ticketList.get(0).getSector());
            assertEquals(response.get(0).getStatusTicket(), ticketList.get(0).getStatusTicket());

            assertEquals(response.get(1).getChair(), ticketList.get(1).getChair());
            assertEquals(response.get(1).getPrice(), ticketList.get(1).getPrice());
            assertEquals(response.get(1).getSector(), ticketList.get(1).getSector());
            assertEquals(response.get(1).getStatusTicket(), ticketList.get(1).getStatusTicket());

            assertEquals(response.get(2).getChair(), ticketList.get(2).getChair());
            assertEquals(response.get(2).getPrice(), ticketList.get(2).getPrice());
            assertEquals(response.get(2).getSector(), ticketList.get(2).getSector());
            assertEquals(response.get(2).getStatusTicket(), ticketList.get(2).getStatusTicket());
        }

        @Test
        void insertTicketLowerSouth() {
            doReturn(ticketList).when(ticketRepository).saveAll(any());

            ticketList.get(0).setChair(ticketList.get(0).getChair() + 22500);
            ticketList.get(1).setChair(ticketList.get(1).getChair() + 22500);
            ticketList.get(2).setChair(ticketList.get(2).getChair() + 22500);

            var response = ticketManagement.insertTicketLowerSouth(createTicketDTO);

            assertEquals(response.size(), createTicketDTO.numberOfTickets());
            verify(ticketRepository, times(1)).saveAll(any());

            assertEquals(response.get(0).getChair(), ticketList.get(0).getChair());
            assertEquals(response.get(0).getPrice(), ticketList.get(0).getPrice());
            assertEquals(response.get(0).getSector(), ticketList.get(0).getSector());
            assertEquals(response.get(0).getStatusTicket(), ticketList.get(0).getStatusTicket());

            assertEquals(response.get(1).getChair(), ticketList.get(1).getChair());
            assertEquals(response.get(1).getPrice(), ticketList.get(1).getPrice());
            assertEquals(response.get(1).getSector(), ticketList.get(1).getSector());
            assertEquals(response.get(1).getStatusTicket(), ticketList.get(1).getStatusTicket());

            assertEquals(response.get(2).getChair(), ticketList.get(2).getChair());
            assertEquals(response.get(2).getPrice(), ticketList.get(2).getPrice());
            assertEquals(response.get(2).getSector(), ticketList.get(2).getSector());
            assertEquals(response.get(2).getStatusTicket(), ticketList.get(2).getStatusTicket());
        }

        @Test
        void insertTicketUpperSouth() {
            doReturn(ticketList).when(ticketRepository).saveAll(any());

            ticketList.get(0).setChair(ticketList.get(0).getChair() + 28125);
            ticketList.get(1).setChair(ticketList.get(1).getChair() + 28125);
            ticketList.get(2).setChair(ticketList.get(2).getChair() + 28125);

            var response = ticketManagement.insertTicketUpperSouth(createTicketDTO);

            assertEquals(response.size(), createTicketDTO.numberOfTickets());
            verify(ticketRepository, times(1)).saveAll(any());

            assertEquals(response.get(0).getChair(), ticketList.get(0).getChair());
            assertEquals(response.get(0).getPrice(), ticketList.get(0).getPrice());
            assertEquals(response.get(0).getSector(), ticketList.get(0).getSector());
            assertEquals(response.get(0).getStatusTicket(), ticketList.get(0).getStatusTicket());

            assertEquals(response.get(1).getChair(), ticketList.get(1).getChair());
            assertEquals(response.get(1).getPrice(), ticketList.get(1).getPrice());
            assertEquals(response.get(1).getSector(), ticketList.get(1).getSector());
            assertEquals(response.get(1).getStatusTicket(), ticketList.get(1).getStatusTicket());

            assertEquals(response.get(2).getChair(), ticketList.get(2).getChair());
            assertEquals(response.get(2).getPrice(), ticketList.get(2).getPrice());
            assertEquals(response.get(2).getSector(), ticketList.get(2).getSector());
            assertEquals(response.get(2).getStatusTicket(), ticketList.get(2).getStatusTicket());
        }

        @Test
        void insertTicketLowerNorth() {
            doReturn(ticketList).when(ticketRepository).saveAll(any());

            ticketList.get(0).setChair(ticketList.get(0).getChair() + 33750);
            ticketList.get(1).setChair(ticketList.get(1).getChair() + 33750);
            ticketList.get(2).setChair(ticketList.get(2).getChair() + 33750);

            var response = ticketManagement.insertTicketLowerNorth(createTicketDTO);

            assertEquals(response.size(), createTicketDTO.numberOfTickets());
            verify(ticketRepository, times(1)).saveAll(any());

            assertEquals(response.get(0).getChair(), ticketList.get(0).getChair());
            assertEquals(response.get(0).getPrice(), ticketList.get(0).getPrice());
            assertEquals(response.get(0).getSector(), ticketList.get(0).getSector());
            assertEquals(response.get(0).getStatusTicket(), ticketList.get(0).getStatusTicket());

            assertEquals(response.get(1).getChair(), ticketList.get(1).getChair());
            assertEquals(response.get(1).getPrice(), ticketList.get(1).getPrice());
            assertEquals(response.get(1).getSector(), ticketList.get(1).getSector());
            assertEquals(response.get(1).getStatusTicket(), ticketList.get(1).getStatusTicket());

            assertEquals(response.get(2).getChair(), ticketList.get(2).getChair());
            assertEquals(response.get(2).getPrice(), ticketList.get(2).getPrice());
            assertEquals(response.get(2).getSector(), ticketList.get(2).getSector());
            assertEquals(response.get(2).getStatusTicket(), ticketList.get(2).getStatusTicket());
        }

        @Test
        void insertTicketUpperNorth() {
            doReturn(ticketList).when(ticketRepository).saveAll(any());

            ticketList.get(0).setChair(ticketList.get(0).getChair() + 39375);
            ticketList.get(1).setChair(ticketList.get(1).getChair() + 39375);
            ticketList.get(2).setChair(ticketList.get(2).getChair() + 39375);

            var response = ticketManagement.insertTicketUpperNorth(createTicketDTO);

            assertEquals(response.size(), createTicketDTO.numberOfTickets());
            verify(ticketRepository, times(1)).saveAll(any());

            assertEquals(response.get(0).getChair(), ticketList.get(0).getChair());
            assertEquals(response.get(0).getPrice(), ticketList.get(0).getPrice());
            assertEquals(response.get(0).getSector(), ticketList.get(0).getSector());
            assertEquals(response.get(0).getStatusTicket(), ticketList.get(0).getStatusTicket());

            assertEquals(response.get(1).getChair(), ticketList.get(1).getChair());
            assertEquals(response.get(1).getPrice(), ticketList.get(1).getPrice());
            assertEquals(response.get(1).getSector(), ticketList.get(1).getSector());
            assertEquals(response.get(1).getStatusTicket(), ticketList.get(1).getStatusTicket());

            assertEquals(response.get(2).getChair(), ticketList.get(2).getChair());
            assertEquals(response.get(2).getPrice(), ticketList.get(2).getPrice());
            assertEquals(response.get(2).getSector(), ticketList.get(2).getSector());
            assertEquals(response.get(2).getStatusTicket(), ticketList.get(2).getStatusTicket());
        }

        @Test
        @DisplayName("Should throw exception when inserting tickets in the lower east sector with invalid count")
        void insertTicketLowerEastError() {
            assertThrows(NumberOfTicketsLargerThanTheSectorException.class,
                    () -> ticketManagement.insertTicketLowerEast(errorCreateExample));

            verify(ticketRepository, times(0)).saveAll(any());
        }

        @Test
        @DisplayName("Should throw exception when inserting tickets in the upper east sector with invalid count")
        void insertTicketUpperEastError() {
            assertThrows(NumberOfTicketsLargerThanTheSectorException.class,
                    () -> ticketManagement.insertTicketUpperEast(errorCreateExample));

            verify(ticketRepository, times(0)).saveAll(any());
        }

        @Test
        @DisplayName("Should throw exception when inserting tickets in the lower west sector with invalid count")
        void insertTicketLowerWestError() {
            assertThrows(NumberOfTicketsLargerThanTheSectorException.class,
                    () -> ticketManagement.insertTicketLowerWest(errorCreateExample));

            verify(ticketRepository, times(0)).saveAll(any());
        }

        @Test
        @DisplayName("Should throw exception when inserting tickets in the upper west sector with invalid count")
        void insertTicketUpperWestError() {
            assertThrows(NumberOfTicketsLargerThanTheSectorException.class,
                    () -> ticketManagement.insertTicketUpperWest(errorCreateExample));

            verify(ticketRepository, times(0)).saveAll(any());
        }

        @Test
        @DisplayName("Should throw exception when inserting tickets in the lower south sector with invalid count")
        void insertTicketLowerSouthError() {
            assertThrows(NumberOfTicketsLargerThanTheSectorException.class,
                    () -> ticketManagement.insertTicketLowerSouth(errorCreateExample));

            verify(ticketRepository, times(0)).saveAll(any());
        }

        @Test
        @DisplayName("Should throw exception when inserting tickets in the upper south sector with invalid count")
        void insertTicketUpperSouthError() {
            assertThrows(NumberOfTicketsLargerThanTheSectorException.class,
                    () -> ticketManagement.insertTicketUpperSouth(errorCreateExample));

            verify(ticketRepository, times(0)).saveAll(any());
        }

        @Test
        @DisplayName("Should throw exception when inserting tickets in the lower north sector with invalid count")
        void insertTicketLowerNorthError() {
            assertThrows(NumberOfTicketsLargerThanTheSectorException.class,
                    () -> ticketManagement.insertTicketLowerNorth(errorCreateExample));

            verify(ticketRepository, times(0)).saveAll(any());
        }

        @Test
        @DisplayName("Should throw exception when inserting tickets in the upper north sector with invalid count")
        void insertTicketUpperNorthError() {
            assertThrows(NumberOfTicketsLargerThanTheSectorException.class,
                    () -> ticketManagement.insertTicketUpperNorth(errorCreateExample));

            verify(ticketRepository, times(0)).saveAll(any());
        }

    }
    
    @Nested
    class DeleteAllTicketsSuccessfully {
        @Test
        @DisplayName("Should delete all tickets successfully")
        void deleteAllTicketsSuccessfully() {
            doNothing()
                    .when(ticketRepository)
                    .deleteAll();

            ticketManagement.deleteAllTickets();

            verify(ticketRepository, times(1)).deleteAll();
        }
    }
}