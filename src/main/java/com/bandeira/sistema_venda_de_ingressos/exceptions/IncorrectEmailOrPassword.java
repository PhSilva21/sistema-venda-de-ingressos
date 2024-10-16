package com.bandeira.sistema_venda_de_ingressos.exceptions;

public class IncorrectEmailOrPassword extends RuntimeException{


    public IncorrectEmailOrPassword(){
        super("Incorrect email or password.");
    }


    public IncorrectEmailOrPassword(String message){
        super(message);
    }
}
