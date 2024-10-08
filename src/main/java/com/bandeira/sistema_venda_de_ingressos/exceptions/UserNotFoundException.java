package com.bandeira.sistema_venda_de_ingressos.exceptions;

public class UserNotFoundException extends RuntimeException{


    public UserNotFoundException(){
        super("User not found.");
    }

    public UserNotFoundException(String message){
        super(message);
    }
}
