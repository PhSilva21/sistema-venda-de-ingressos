package com.bandeira.sistema_venda_de_ingressos.services.impl;

import com.bandeira.sistema_venda_de_ingressos.models.User;
import com.bandeira.sistema_venda_de_ingressos.models.enums.UserRole;
import com.bandeira.sistema_venda_de_ingressos.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    AuthServiceImpl authorizationService;


    User user = new User(
            "Lucas Walter",
            "marcos@gmail.com",
            "marcos33",
            "813663617351",
            UserRole.USER
    );

    @Nested
    class loadByUserName {

        @Test
        @DisplayName("Must return user successfully")
        void MustReturnUserSuccessfully() {
            doReturn(user)
                    .when(userRepository)
                    .findByEmail(user.getUsername());

            var response = authorizationService.loadUserByUsername(user.getUsername());

            assertNotNull(response);
            assertEquals(user.getUsername(), response.getUsername());
            assertEquals(user.getPassword(), response.getPassword());
        }

        @Test
        @DisplayName("Should throw exception when not finding user")
        void ShouldThrowExceptionWhenNotFindingUser() {
            doThrow(new UsernameNotFoundException(""))
                    .when(userRepository)
                    .findByEmail(user.getEmail());

            assertThrows(UsernameNotFoundException.class,
                    () -> authorizationService.loadUserByUsername(user.getUsername()));
        }
    }
}