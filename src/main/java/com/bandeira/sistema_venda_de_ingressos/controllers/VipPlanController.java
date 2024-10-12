package com.bandeira.sistema_venda_de_ingressos.controllers;

import com.bandeira.sistema_venda_de_ingressos.dtos.CreatePlanDTO;
import com.bandeira.sistema_venda_de_ingressos.dtos.UpdatePlanDTO;
import com.bandeira.sistema_venda_de_ingressos.models.Plan;
import com.bandeira.sistema_venda_de_ingressos.services.VipPlanService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("api/v1/plans")
@RequiredArgsConstructor
public class VipPlanController {

    private final VipPlanService vipPlanService;

    @PostMapping("/buy-silver-plan")
    public ResponseEntity<Void> buySilverPlan(@RequestBody Long id) throws MessagingException, UnsupportedEncodingException {
        vipPlanService.buySilverPlan(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/buy-gold-plan")
    public ResponseEntity<Void> buyGoldPlan(@RequestBody Long id) throws MessagingException, UnsupportedEncodingException {
        vipPlanService.buyGoldPlan(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/buy-emerald-plan")
    public ResponseEntity<Void> buyEmeraldPlan(@RequestBody Long id) throws MessagingException, UnsupportedEncodingException {
        vipPlanService.buyEmeraldPlan(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/buy-diamond-plan")
    public ResponseEntity<Void> buyDiamondPlan(@RequestBody Long id) throws MessagingException, UnsupportedEncodingException {
        vipPlanService.buyDiamondPlan(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("create-plan")
    public ResponseEntity<Plan> createPlan(@RequestBody CreatePlanDTO request) {
        Plan plan = vipPlanService.createPlan(request);
        return ResponseEntity.ok().body(plan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        vipPlanService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<Plan> updatePlan(@RequestBody UpdatePlanDTO request) {
        Plan plan = vipPlanService.updatePlan(request);
        return ResponseEntity.ok().body(plan);
    }
}
