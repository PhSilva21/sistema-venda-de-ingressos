package com.bandeira.sistema_venda_de_ingressos.exceptions;

public class TicketsSoldOut extends RuntimeException{


    public TicketsSoldOut(){
        super("Ingressos esgotados");
    }

    public TicketsSoldOut(String message){
        super(message);
    }
}
