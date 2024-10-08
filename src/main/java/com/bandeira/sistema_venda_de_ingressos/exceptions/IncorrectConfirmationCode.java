package com.bandeira.sistema_venda_de_ingressos.exceptions;

public class IncorrectConfirmationCode extends RuntimeException{


    public IncorrectConfirmationCode(){
        super("Incorrect conformation code.");
    }

    public IncorrectConfirmationCode(String message){
        super(message)  ;
    }
}
