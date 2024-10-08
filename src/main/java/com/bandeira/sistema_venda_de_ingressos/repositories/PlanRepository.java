package com.bandeira.sistema_venda_de_ingressos.repositories;

import com.bandeira.sistema_venda_de_ingressos.models.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
