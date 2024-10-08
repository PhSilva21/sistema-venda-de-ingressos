package com.bandeira.sistema_venda_de_ingressos.services.impl;

import com.bandeira.sistema_venda_de_ingressos.models.User;
import com.bandeira.sistema_venda_de_ingressos.services.EmailService;
import com.bandeira.sistema_venda_de_ingressos.util.RandomString;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class EmailServiceImpl implements EmailService {


    private final JavaMailSender emailSender;


    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public String setFrom = "pedro.amp002@gmail.com";
    public String senderName = "Estádio Hipotético";


    @Override
    public void sendEmailCreateUser(User user) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(setFrom, senderName);
        helper.setTo(user.getEmail());
        helper.setSubject("Codigo...");
        helper.setText("Verifique seu codigo...");


        emailSender.send(message);
    }

    @Override
    public String confirmationEmail(User user) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(setFrom, senderName);
        helper.setTo(user.getEmail());
        helper.setSubject("Email de confirmção...");

        var code = RandomString.generateRandomString(6);

        helper.setText(code);

        emailSender.send(message);

        return code;
    }

    @Override
    public void sendEmailBuyTicket(User user) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(setFrom, senderName);
        helper.setTo(user.getEmail());
        helper.setSubject("Confirmamos seu pagamento...");
        helper.setText("Te esperamos no dia xx/xx...");


        emailSender.send(message);

    }

    @Override
    public void sendEmailBuySilverPlan(User user) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(setFrom, senderName);
        helper.setTo(user.getEmail());
        helper.setSubject("Confirmamos seu pagamento...");
        helper.setText("Revise seus novos beneficios...");


        emailSender.send(message);
    }

    @Override
    public void sendEmailBuyGoldPlan(User user) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(setFrom, senderName);
        helper.setTo(user.getEmail());
        helper.setSubject("Confirmamos seu pagamento...");
        helper.setText("Revise seus novos beneficios...");


        emailSender.send(message);
    }

    @Override
    public void sendEmailBuyEmeraldPlan(User user) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(setFrom, senderName);
        helper.setTo(user.getEmail());
        helper.setSubject("Confirmamos seu pagamento......");
        helper.setText("Revise seus novos beneficios...");


        emailSender.send(message);
    }

    @Override
    public void sendEmailBuyDiamondPlan(User user) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(setFrom, senderName);
        helper.setTo(user.getEmail());
        helper.setSubject("Confirmamos seu pagamento......");
        helper.setText("Revise seus novos beneficios...");


        emailSender.send(message);
    }


}
