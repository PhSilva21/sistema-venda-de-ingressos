package com.bandeira.sistema_venda_de_ingressos.exceptions;

public class PlanNotFoundException extends RuntimeException{


    public PlanNotFoundException(){
        super("Plan not found exception");
    }

    public PlanNotFoundException(String message){
        super(message);
    }
}
