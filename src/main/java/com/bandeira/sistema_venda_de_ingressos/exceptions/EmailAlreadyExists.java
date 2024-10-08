package com.bandeira.sistema_venda_de_ingressos.exceptions;

public class EmailAlreadyExists extends RuntimeException{


    public EmailAlreadyExists(){
        super("Email already exists.");
    }

    public EmailAlreadyExists(String message){
        super(message);
    }
}
