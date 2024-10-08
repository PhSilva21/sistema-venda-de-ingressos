package com.bandeira.sistema_venda_de_ingressos.exceptions;

public class IncorrectPasswordException extends RuntimeException{


    public IncorrectPasswordException(){
        super("Incorrect password.");
    }

    public IncorrectPasswordException(String message){
        super(message);
    }
}
