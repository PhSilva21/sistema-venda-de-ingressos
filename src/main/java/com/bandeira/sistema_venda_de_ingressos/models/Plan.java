package com.bandeira.sistema_venda_de_ingressos.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "tb_plans")
@Entity
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    private List<Plan> vipsUsers = new ArrayList<>();


    public Plan(String name, BigDecimal price) {
        this.name = name;
        this.price = price;

    }
}
