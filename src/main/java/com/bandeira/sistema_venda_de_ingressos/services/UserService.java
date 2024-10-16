package com.bandeira.sistema_venda_de_ingressos.services;

import com.bandeira.sistema_venda_de_ingressos.dtos.*;
import com.bandeira.sistema_venda_de_ingressos.models.User;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.UnsupportedEncodingException;

public interface UserService {


    void createUser(CreateUserDTO request) throws MessagingException, UnsupportedEncodingException;

    LoginResponse login(LoginUserDTO request);

    User findById(Long id);

    void updateUser(UpdateUserDTO updateUserDTO);

    void deleteById(Long id);

    void updateEmail(UpdateEmailDTO updateEmailDTO) throws MessagingException, UnsupportedEncodingException;

    void updatePassword(UpdatePasswordDTO updatePasswordDTO) throws MessagingException, UnsupportedEncodingException;

    void confirmationCode(User user, String code) throws MessagingException, UnsupportedEncodingException;

    String encryptedPassword(String password);
}
