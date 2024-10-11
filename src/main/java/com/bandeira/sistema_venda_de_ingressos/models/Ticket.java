package com.bandeira.sistema_venda_de_ingressos.models;

import com.bandeira.sistema_venda_de_ingressos.models.enums.SectorTicket;
import com.bandeira.sistema_venda_de_ingressos.models.enums.StatusTicket;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "tb_tickets")
@Entity
public class Ticket {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;


    private Long chair;


    private BigDecimal price;


    private SectorTicket sector;


    private StatusTicket statusTicket;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    public Ticket(BigDecimal price, Long chair, LocalDate date, SectorTicket sector
            , StatusTicket statusTicket) {
        this.price = price;
        this.chair = chair;
        this.date = date;
        this.sector = sector;
        this.statusTicket = statusTicket;
    }
}
