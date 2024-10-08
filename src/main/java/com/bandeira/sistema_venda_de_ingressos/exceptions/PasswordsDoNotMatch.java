package com.bandeira.sistema_venda_de_ingressos.exceptions;

public class PasswordsDoNotMatch extends RuntimeException{


    public PasswordsDoNotMatch(){
        super("Password do no match");
    }

    public PasswordsDoNotMatch(String message){
        super(message);
    }
}
