package com.bandeira.sistema_venda_de_ingressos.exceptions;

public class NumberOfTicketsLargerThanTheSectorException extends RuntimeException{

    public NumberOfTicketsLargerThanTheSectorException(){
        super("The number of tickets is greater than the number of seats in the sector");
    }

    public NumberOfTicketsLargerThanTheSectorException(String message){
        super(message);
    }
}
