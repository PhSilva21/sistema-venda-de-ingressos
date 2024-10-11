package com.bandeira.sistema_venda_de_ingressos.services;

import com.bandeira.sistema_venda_de_ingressos.dtos.BuyPlanDTO;
import com.bandeira.sistema_venda_de_ingressos.dtos.CreatePlanDTO;
import com.bandeira.sistema_venda_de_ingressos.dtos.UpdatePlanDTO;
import com.bandeira.sistema_venda_de_ingressos.models.Plan;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface VipPlanService {

    void buySilverPlan(BuyPlanDTO request) throws MessagingException, UnsupportedEncodingException;

    void buyGoldPlan(BuyPlanDTO request) throws MessagingException, UnsupportedEncodingException;

    void buyEmeraldPlan(BuyPlanDTO request) throws MessagingException, UnsupportedEncodingException;

    void buyDiamondPlan(BuyPlanDTO request) throws MessagingException, UnsupportedEncodingException;

    Plan createPlan(CreatePlanDTO request);

    void deleteById(Long id);

    Plan updatePlan(UpdatePlanDTO request);

}
