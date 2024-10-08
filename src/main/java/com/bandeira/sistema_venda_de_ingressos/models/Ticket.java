package com.bandeira.sistema_venda_de_ingressos.models;

import com.bandeira.sistema_venda_de_ingressos.models.enums.StatusTicket;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    private LocalDate date;


    private Long chair;


    private BigDecimal price;


    private StatusTicket statusTicket;


    public Ticket(Long chair, BigDecimal price , StatusTicket statusTicket) {
        this.chair = chair;
        this.price = price;
        this.statusTicket = statusTicket;
    }
}
