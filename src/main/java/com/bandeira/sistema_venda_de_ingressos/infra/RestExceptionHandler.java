package com.bandeira.sistema_venda_de_ingressos.infra;

import com.bandeira.sistema_venda_de_ingressos.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(EmailAlreadyExists.class)
    private ResponseEntity<String> emailAlreadyExist(EmailAlreadyExists exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Este email já está cadastrado, faça login.");
    }

    @ExceptionHandler(IncorrectConfirmationCode.class)
    private ResponseEntity<String> incorrectConfirmationCode(IncorrectConfirmationCode exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Cádigo de confirmação incorreto.");
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    private ResponseEntity<String> incorrectPassword(IncorrectPasswordException exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Senha incorreta.");
    }

    @ExceptionHandler(IncorrectEmailOrPassword.class)
    private ResponseEntity<String> incorrectEmailOrPassword(IncorrectEmailOrPassword exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email ou senha incorreto.");
    }

    @ExceptionHandler(PasswordsDoNotMatch.class)
    private ResponseEntity<String> passwordDoNotMatch(PasswordsDoNotMatch exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("As senhas não são iguais.");
    }

    @ExceptionHandler(PlanNotFoundException.class)
    private ResponseEntity<String> planNotFoundException(PlanNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plano não encontrado.");
    }

    @ExceptionHandler(TicketsSoldOut.class)
    private ResponseEntity<String> ticketsSoldOut(TicketsSoldOut exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ingressos esgotados");
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<String> userNotFoundException(UserNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
    }

}
