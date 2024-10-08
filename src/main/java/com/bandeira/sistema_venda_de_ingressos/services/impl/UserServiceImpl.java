package com.bandeira.sistema_venda_de_ingressos.services.impl;


import com.bandeira.sistema_venda_de_ingressos.dtos.CreateUserDTO;
import com.bandeira.sistema_venda_de_ingressos.dtos.UpdateEmailDTO;
import com.bandeira.sistema_venda_de_ingressos.dtos.UpdatePasswordDTO;
import com.bandeira.sistema_venda_de_ingressos.dtos.UpdateUserDTO;
import com.bandeira.sistema_venda_de_ingressos.exceptions.*;
import com.bandeira.sistema_venda_de_ingressos.models.User;
import com.bandeira.sistema_venda_de_ingressos.repositories.UserRepository;
import com.bandeira.sistema_venda_de_ingressos.services.EmailService;
import com.bandeira.sistema_venda_de_ingressos.services.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;


    @Override
    public void createUser(CreateUserDTO request) throws MessagingException, UnsupportedEncodingException {
        if(userRepository.findByEmail(request.email()) != null){
            throw new EmailAlreadyExists();
        }

        User user = new User(
                request.name(),
                request.email(),
                encryptedPassword(request.password()),
                request.cpf(),
                request.userRole()
        );

        userRepository.save(user);

        emailService.sendEmailCreateUser(user);
    }

    @Override
    public UserDetails findById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void updateUser(UpdateUserDTO updateUserDTO) {
        var user = userRepository.findById(updateUserDTO.id())
                .orElseThrow(UserNotFoundException::new);

        user.setName(updateUserDTO.name());
        user.setCpf(updateUserDTO.cpf());

        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        userRepository.deleteById(id);
    }

    @Override
    public void updateEmail(UpdateEmailDTO request) throws MessagingException, UnsupportedEncodingException {
        var user = userRepository.findById(request.id())
                .orElseThrow(UserNotFoundException::new);

        confirmationCode(user, request.code());

        user.setEmail(request.email());

        userRepository.save(user);
    }

    @Override
    public void updatePassword(UpdatePasswordDTO request) {
        var user = userRepository.findById(request.id())
                .orElseThrow(UserNotFoundException::new);

        if (!user.getPassword().equals(request.password())){
            throw new IncorrectPasswordException();
        }

        if (!request.newPassword().equals(request.newPasswordConfirmation())){
            throw new PasswordsDoNotMatch();
        }

        user.setPassword(encryptedPassword(request.newPassword()));

        userRepository.save(user);
    }

    @Override
    public void confirmationCode(User user, String code) throws MessagingException, UnsupportedEncodingException {
        if(!emailService.confirmationEmail(user).equals(code)){
            throw new IncorrectConfirmationCode();
        }
    }


    public String encryptedPassword(String password){
        return passwordEncoder.encode(password);
    }


}
