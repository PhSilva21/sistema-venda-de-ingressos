package com.bandeira.sistema_venda_de_ingressos.services;

import com.bandeira.sistema_venda_de_ingressos.models.User;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface EmailService {


    void sendEmailCreateUser(User user) throws MessagingException, UnsupportedEncodingException;

    String confirmationEmail(User user) throws MessagingException, UnsupportedEncodingException;

    void sendEmailBuyTicket(User user) throws MessagingException, UnsupportedEncodingException;

    void sendEmailBuySilverPlan(User user) throws MessagingException, UnsupportedEncodingException;

    void sendEmailBuyGoldPlan(User user) throws MessagingException, UnsupportedEncodingException;

    void sendEmailBuyEmeraldPlan(User user) throws MessagingException, UnsupportedEncodingException;

    void sendEmailBuyDiamondPlan(User user) throws MessagingException, UnsupportedEncodingException;
}
