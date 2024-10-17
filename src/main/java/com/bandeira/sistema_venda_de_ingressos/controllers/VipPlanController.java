package com.bandeira.sistema_venda_de_ingressos.controllers;

import com.bandeira.sistema_venda_de_ingressos.dtos.CreatePlanDTO;
import com.bandeira.sistema_venda_de_ingressos.dtos.UpdatePlanDTO;
import com.bandeira.sistema_venda_de_ingressos.models.Plan;
import com.bandeira.sistema_venda_de_ingressos.services.VipPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("api/v1/plans")
@RequiredArgsConstructor
public class VipPlanController {

    private final VipPlanService vipPlanService;


    @Operation(description = "Operação para adquirir plano prata")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plano adquirido com sucesso"),
            @ApiResponse(responseCode = "417", description = "Erro de validação de dados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/buy-silver-plan")
    public ResponseEntity<Void> buySilverPlan(@RequestBody Long id) throws MessagingException, UnsupportedEncodingException {
        vipPlanService.buySilverPlan(id);
        return ResponseEntity.ok().build();
    }


    @Operation(description = "Operação para adquirir plano ouro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plano adquirido com sucesso"),
            @ApiResponse(responseCode = "417", description = "Erro de validação de dados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/buy-gold-plan")
    public ResponseEntity<Void> buyGoldPlan(@RequestBody Long id) throws MessagingException, UnsupportedEncodingException {
        vipPlanService.buyGoldPlan(id);
        return ResponseEntity.ok().build();
    }


    @Operation(description = "Operação para adquirir plano esmeralda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plano adquirido com sucesso"),
            @ApiResponse(responseCode = "417", description = "Erro de validação de dados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/buy-emerald-plan")
    public ResponseEntity<Void> buyEmeraldPlan(@RequestBody Long id) throws MessagingException, UnsupportedEncodingException {
        vipPlanService.buyEmeraldPlan(id);
        return ResponseEntity.ok().build();
    }


    @Operation(description = "Operação para adquirir plano diamante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plano adquirido com sucesso"),
            @ApiResponse(responseCode = "417", description = "Erro de validação de dados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/buy-diamond-plan")
    public ResponseEntity<Void> buyDiamondPlan(@RequestBody Long id) throws MessagingException, UnsupportedEncodingException {
        vipPlanService.buyDiamondPlan(id);
        return ResponseEntity.ok().build();
    }


    @Operation(description = "Operação para criar um novo plano")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plano criado com sucesso"),
            @ApiResponse(responseCode = "417", description = "Erro de validação de dados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("create-plan")
    public ResponseEntity<Plan> createPlan(@RequestBody @Valid CreatePlanDTO request) {
        Plan plan = vipPlanService.createPlan(request);
        return ResponseEntity.ok().body(plan);
    }


    @Operation(description = "Operação para deletar um plano")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plano deletado com sucesso"),
            @ApiResponse(responseCode = "417", description = "Erro de validação de dados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        vipPlanService.deleteById(id);
        return ResponseEntity.ok().build();
    }


    @Operation(description = "Operação para atualizar um plano")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plano atualizado com sucesso"),
            @ApiResponse(responseCode = "417", description = "Erro de validação de dados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/update-plan")
    public ResponseEntity<Plan> updatePlan(@RequestBody @Valid UpdatePlanDTO request) {
        Plan plan = vipPlanService.updatePlan(request);
        return ResponseEntity.ok().body(plan);
    }
}
