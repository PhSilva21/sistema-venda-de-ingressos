package com.bandeira.sistema_venda_de_ingressos.services;

import com.bandeira.sistema_venda_de_ingressos.dtos.*;
import com.bandeira.sistema_venda_de_ingressos.models.User;
import jakarta.mail.MessagingException;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.UnsupportedEncodingException;

public interface UserService {


    void createUser(CreateUserDTO createUserDTO) throws MessagingException, UnsupportedEncodingException;

    UserDetails findById(Long id);

    void updateUser(UpdateUserDTO updateUserDTO);

    void deleteById(Long id);

    void updateEmail(UpdateEmailDTO updateEmailDTO) throws MessagingException, UnsupportedEncodingException;

    void updatePassword(UpdatePasswordDTO updatePasswordDTO);

    void confirmationCode(User user, String code) throws MessagingException, UnsupportedEncodingException;
}
