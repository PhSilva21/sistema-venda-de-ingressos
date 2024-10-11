package com.bandeira.sistema_venda_de_ingressos.controllers;

import com.bandeira.sistema_venda_de_ingressos.dtos.CreateUserDTO;
import com.bandeira.sistema_venda_de_ingressos.dtos.UpdateEmailDTO;
import com.bandeira.sistema_venda_de_ingressos.dtos.UpdatePasswordDTO;
import com.bandeira.sistema_venda_de_ingressos.dtos.UpdateUserDTO;
import com.bandeira.sistema_venda_de_ingressos.models.User;
import com.bandeira.sistema_venda_de_ingressos.services.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;




    @GetMapping("/{id}")
    public ResponseEntity<UserDetails> findById(@PathVariable Long id) {
        var user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/update-user")
    public ResponseEntity<Void> updateUser(@RequestBody UpdateUserDTO updateUserDTO) {
        userService.updateUser(updateUserDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update-email")
    public ResponseEntity<Void> updateEmail(@RequestBody UpdateEmailDTO request) throws MessagingException, UnsupportedEncodingException {
        userService.updateEmail(request);
        return  ResponseEntity.ok().build();
    }


}
