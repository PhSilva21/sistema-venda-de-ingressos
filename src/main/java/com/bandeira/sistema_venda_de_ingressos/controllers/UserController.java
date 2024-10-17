package com.bandeira.sistema_venda_de_ingressos.controllers;

import com.bandeira.sistema_venda_de_ingressos.dtos.*;
import com.bandeira.sistema_venda_de_ingressos.models.User;
import com.bandeira.sistema_venda_de_ingressos.services.TokenService;
import com.bandeira.sistema_venda_de_ingressos.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @Operation(description = "Operação para criar usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario criado com sucesso"),
            @ApiResponse(responseCode = "417", description = "Erro de validação de dados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("create-user")
    public ResponseEntity<UserDetails> createUser(@RequestBody @Valid CreateUserDTO request)
            throws MessagingException, UnsupportedEncodingException {
        userService.createUser(request);
        return ResponseEntity.ok().build();
    }


    @Operation(description = "Operação para logar o usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario logado com sucesso"),
            @ApiResponse(responseCode = "417", description = "Erro de validação de dados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginUserDTO request){
        var response = userService.login(request);
        return ResponseEntity.ok().body(response);
    }

    @Operation(description = "Operação trocar a senha")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Senha atualizada com sucesso"),
            @ApiResponse(responseCode = "417", description = "Erro de validação de dados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("update-password")
    public ResponseEntity<UserDetails> updatePassword(@RequestBody @Valid UpdatePasswordDTO request) throws MessagingException, UnsupportedEncodingException {
        userService.updatePassword(request);
        return ResponseEntity.ok().build();
    }


    @Operation(description = "Operação para buscar usuário por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario criado com sucesso"),
            @ApiResponse(responseCode = "417", description = "Erro de validação de dados"),
            @ApiResponse(responseCode = "500", description = "Erro intern do servidor")
    })
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        var user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }


    @Operation(description = "Operação para atualizar dados do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario criado com sucesso"),
            @ApiResponse(responseCode = "417", description = "Erro de validação de dados"),
            @ApiResponse(responseCode = "500", description = "Erro intern do servidor")
    })
    @PostMapping("/update-user")
    public ResponseEntity<Void> updateUser(@RequestBody @Valid UpdateUserDTO updateUserDTO) {
        userService.updateUser(updateUserDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(description = "Operação para deletar usuário por is")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario criado com sucesso"),
            @ApiResponse(responseCode = "417", description = "Erro de validação de dados"),
            @ApiResponse(responseCode = "500", description = "Erro intern do servidor")
    })
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Operation(description = "Operação para atualizar email do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario criado com sucesso"),
            @ApiResponse(responseCode = "417", description = "Erro de validação de dados"),
            @ApiResponse(responseCode = "500", description = "Erro intern do servidor")
    })
    @PutMapping("/update-email")
    public ResponseEntity<Void> updateEmail(@RequestBody @Valid UpdateEmailDTO request) throws MessagingException, UnsupportedEncodingException {
        userService.updateEmail(request);
        return  ResponseEntity.ok().build();
    }


}
