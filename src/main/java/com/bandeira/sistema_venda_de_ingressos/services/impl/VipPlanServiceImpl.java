package com.bandeira.sistema_venda_de_ingressos.services.impl;

import com.bandeira.sistema_venda_de_ingressos.dtos.BuyPlanDTO;
import com.bandeira.sistema_venda_de_ingressos.dtos.CreatePlanDTO;
import com.bandeira.sistema_venda_de_ingressos.dtos.UpdatePlanDTO;
import com.bandeira.sistema_venda_de_ingressos.exceptions.PlanNotFoundException;
import com.bandeira.sistema_venda_de_ingressos.exceptions.UserNotFoundException;

import com.bandeira.sistema_venda_de_ingressos.models.Plan;
import com.bandeira.sistema_venda_de_ingressos.repositories.PlanRepository;
import com.bandeira.sistema_venda_de_ingressos.repositories.UserRepository;
import com.bandeira.sistema_venda_de_ingressos.services.EmailService;
import com.bandeira.sistema_venda_de_ingressos.services.VipPlanService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class VipPlanServiceImpl implements VipPlanService {

    private final UserRepository userRepository;

    private final PlanRepository planRepository;

    private final EmailService emailService;

    @Override
    public void buySilverPlan(BuyPlanDTO request) throws MessagingException, UnsupportedEncodingException {
        var user = userRepository.findById(request.userId())
                .orElseThrow(UserNotFoundException::new);

        var plan = planRepository.findById(1L).orElseThrow(RuntimeException::new);

        user.setPoints(2L);

        emailService.sendEmailBuySilverPlan(user);
    }

    @Override
    public void buyGoldPlan(BuyPlanDTO request) throws MessagingException, UnsupportedEncodingException {
        var user = userRepository.findById(request.userId())
                .orElseThrow(UserNotFoundException::new);

        var plan = planRepository.findById(2L).orElseThrow(RuntimeException::new);


        user.setPoints(4L);

        emailService.sendEmailBuyGoldPlan(user);
    }

    @Override
    public void buyEmeraldPlan(BuyPlanDTO request) throws MessagingException, UnsupportedEncodingException {
        var user = userRepository.findById(request.userId())
                .orElseThrow(UserNotFoundException::new);

        var plan = planRepository.findById(3L).orElseThrow(RuntimeException::new);


        user.setPoints(6L);

        emailService.sendEmailBuyEmeraldPlan(user);
    }

    @Override
    public void buyDiamondPlan(BuyPlanDTO request) throws MessagingException, UnsupportedEncodingException {
        var user = userRepository.findById(request.userId())
                .orElseThrow(UserNotFoundException::new);

        var plan = planRepository.findById(4L).orElseThrow(RuntimeException::new);


        user.setPoints(10L);

        emailService.sendEmailBuyDiamondPlan(user);
    }

    @Override
    public Plan createPlan(CreatePlanDTO request) {
        return new Plan(
                request.name(),
                request.price()
        );
    }


    @Override
    public void deleteById(Long id) {
        var plan = planRepository.findById(id).orElseThrow(PlanNotFoundException::new);

        planRepository.deleteById(id);
    }

    @Override
    public Plan updatePlan(UpdatePlanDTO request) {
        var plan = planRepository.findById(request.id()).orElseThrow(PlanNotFoundException::new);

        plan.setName(request.name());
        plan.setPrice(request.price());

        return planRepository.save(plan);
    }


}

