package com.bandeira.sistema_venda_de_ingressos.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bandeira.sistema_venda_de_ingressos.dtos.*;
import com.bandeira.sistema_venda_de_ingressos.exceptions.*;
import com.bandeira.sistema_venda_de_ingressos.models.User;
import com.bandeira.sistema_venda_de_ingressos.models.enums.UserRole;
import com.bandeira.sistema_venda_de_ingressos.repositories.UserRepository;
import com.bandeira.sistema_venda_de_ingressos.services.EmailService;
import com.bandeira.sistema_venda_de_ingressos.services.TokenService;
import com.bandeira.sistema_venda_de_ingressos.services.UserService;
import com.bandeira.sistema_venda_de_ingressos.util.RandomString;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Mock
    UserService userService;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    UserRepository userRepository;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    EmailService emailService;

    @Mock
    TokenService tokenService;

    @Captor
    ArgumentCaptor<User> userArgumentCaptor;

    @Nested
    class createUser {

        User user = new User("Marco", "marco@gmail.com", "1234", "2637232314311"
                , UserRole.USER);

        CreateUserDTO userRequest = new CreateUserDTO("Joao", "marcia@gmail.com", "91212", "2637232314311"
                , UserRole.USER);


        @Test
        @DisplayName("Must create user successfully")
        void MustCreateUserSuccessfully() throws JsonProcessingException, MessagingException, UnsupportedEncodingException {

            var random = RandomString.generateRandomString(44);

            doReturn(null)
                    .when(userRepository)
                    .findByEmail(userRequest.email());
            doReturn(userRequest.password())
                    .when(passwordEncoder)
                    .encode(userRequest.password());
            doReturn(user)
                    .when(userRepository)
                    .save(userArgumentCaptor.capture());

            userServiceImpl.createUser(userRequest);

            var userCaptured = userArgumentCaptor.getValue();

            assertEquals(userRequest.email(), userCaptured.getEmail());
            assertEquals(userRequest.password(), userCaptured.getPassword());
            assertEquals(userRequest.email(), userCaptured.getEmail());
            assertEquals(userRequest.cpf(), userCaptured.getCpf());
            assertEquals(userRequest.userRole(), userCaptured.getUserRole());


            verify(userRepository, times(1))
                    .findByEmail(userRequest.email());
            verify(passwordEncoder, times(1))
                    .encode(userRequest.password());
            verify(userRepository, times(1))
                    .save(userCaptured);
        }

        @Test
        @DisplayName("Must return exception when the email already exists")
        void MustReturnExceptionWhenTheEmailAlreadyExists() {
            doReturn(user)
                    .when(userRepository)
                    .findByEmail(userRequest.email());

            assertThrows(EmailAlreadyExists.class, () ->
                    userServiceImpl.createUser(userRequest));

            verify(userRepository, times(1))
                    .findByEmail(userRequest.email());
            verify(passwordEncoder, times(0))
                    .encode(userRequest.password());
            verify(userRepository, times(0))
                    .save(userArgumentCaptor.capture());
        }
    }

    @Nested
    class updateEmail {

        User user = new User("Marco", "marco@gmail.com", "1234", "2637232314311"
                , UserRole.USER);

        String code = RandomString.generateRandomString(6);

        UpdateEmailDTO updateEmailDTO = new UpdateEmailDTO(8L, "carlos@gmail.com"
                , code);

        @Test
        @DisplayName("Must update email successfully")
        void MustUpdateEmailSuccessfully() throws MessagingException, UnsupportedEncodingException {
            doReturn(Optional.of(user))
                    .when(userRepository)
                    .findById(updateEmailDTO.id());
            doReturn(code).when(emailService)
                    .confirmationEmail(user);
            doReturn(user)
                    .when(userRepository)
                    .save(userArgumentCaptor.capture());

            userServiceImpl.updateEmail(updateEmailDTO);

            var userCaptured = userArgumentCaptor.getValue();

            assertEquals(updateEmailDTO.email(), userCaptured.getEmail());
            assertEquals(code, updateEmailDTO.code());

            verify(userRepository, times(1))
                    .findById(updateEmailDTO.id());
            verify(emailService,times(1)).confirmationEmail(user);
            verify(userRepository, times(1))
                    .save(userArgumentCaptor.capture());
        }

        @Test
        @DisplayName("Should throw exception when the confirmation code is incorrect")
        void ShouldThrowExceptionWhenConfirmationCodeIsIncorrect() throws MessagingException, UnsupportedEncodingException {
            doReturn(Optional.of(user))
                    .when(userRepository)
                    .findById(updateEmailDTO.id());
            doReturn("2163g3").when(emailService)
                    .confirmationEmail(user);


            assertThrows(IncorrectConfirmationCode.class,
                    () -> userServiceImpl.updateEmail(updateEmailDTO));

            verify(userRepository, times(1))
                    .findById(updateEmailDTO.id());
            verify(userRepository, times(0))
                    .save(user);
        }

        @Test
        @DisplayName("Should throw exception when not finding the user")
        void ShouldThrowExceptionWhenNotFindingTheUser() {
            doReturn(Optional.empty())
                    .when(userRepository)
                    .findById(updateEmailDTO.id());

            assertThrows(UserNotFoundException.class,
                    () -> userServiceImpl.updateEmail(updateEmailDTO));

            verify(userRepository, times(1))
                    .findById(updateEmailDTO.id());
            verify(userRepository, times(0))
                    .save(user);
        }
    }


    @Nested
    class updatePassword {

        User user = new User("Marco", "marco@gmail.com", "1234", "2637232314311"
                , UserRole.USER);

        String code = RandomString.generateRandomString(6);


        UpdatePasswordDTO updatePasswordDTO = new UpdatePasswordDTO(9L, code
                , "71ye2e62e2", "71ye2e62e2");

        @Test
        @DisplayName("must update password successfully")
        void MustUpdatePasswordSuccessfully() throws MessagingException, UnsupportedEncodingException {
            doReturn(Optional.of(user))
                    .when(userRepository)
                    .findById(updatePasswordDTO.id());
            doReturn(code).when(emailService)
                    .confirmationEmail(user);
            doReturn(updatePasswordDTO.newPassword())
                    .when(passwordEncoder)
                    .encode(updatePasswordDTO.newPassword());
            doReturn(user)
                    .when(userRepository)
                    .save(userArgumentCaptor.capture());

            userServiceImpl.updatePassword(updatePasswordDTO);

            var userCaptured = userArgumentCaptor.getValue();

            assertEquals(code, updatePasswordDTO.code());

            verify(userRepository, times(1))
                    .findById(updatePasswordDTO.id());
            verify(userRepository, times(1))
                    .save(user);
        }

        @Test
        @DisplayName("Should throw exception when not finding the user")
        void ShouldThrowExceptionWhenNotFindingTheUser() {
            doReturn(Optional.empty())
                    .when(userRepository)
                    .findById(updatePasswordDTO.id());

            assertThrows(UserNotFoundException.class,
                    () -> userServiceImpl.updatePassword(updatePasswordDTO));

            verify(userRepository, times(1))
                    .findById(updatePasswordDTO.id());
            verify(userRepository, times(0))
                    .save(user);
        }
        @Test
        @DisplayName("Should throw exception when the confirmation code is incorrect")
        void ShouldThrowExceptionWhenConfirmationCodeIsIncorrect() throws MessagingException, UnsupportedEncodingException {
            doReturn(Optional.of(user))
                    .when(userRepository)
                    .findById(updatePasswordDTO.id());
            doReturn("2163g3").when(emailService)
                    .confirmationEmail(user);


            assertThrows(IncorrectConfirmationCode.class,
                    () -> userServiceImpl.updatePassword(updatePasswordDTO));

            verify(userRepository, times(1))
                    .findById(updatePasswordDTO.id());
            verify(userRepository, times(0))
                    .save(user);
        }
        @Test
        @DisplayName("Should throw exception when passwords do not match")
        void ShouldThrowExceptionWhenPasswordsDoNotMatch() throws MessagingException, UnsupportedEncodingException {
            UpdatePasswordDTO updatePasswordDTO = new UpdatePasswordDTO(9L, code
                    , "71ye2e62e2", "216251d");

            doReturn(Optional.of(user))
                    .when(userRepository)
                    .findById(updatePasswordDTO.id());
            doReturn(code).when(emailService)
                    .confirmationEmail(user);

            assertThrows(PasswordsDoNotMatch.class,
                    () -> userServiceImpl.updatePassword(updatePasswordDTO));

            verify(userRepository, times(1))
                    .findById(updatePasswordDTO.id());
            verify(userRepository, times(0))
                    .save(user);
        }
    }

    @Nested
    class deleteById {

        User user = new User("Marco", "marco@gmail.com", "1234", "2637232314311"
                , UserRole.USER);

        @Test
        @DisplayName("Must delete user successfully")
        void MustDeleteUserSuccessfully() {
            doReturn(Optional.of(user))
                    .when(userRepository)
                    .findById(user.getId());
            doNothing()
                    .when(userRepository)
                    .deleteById(user.getId());

            userServiceImpl.deleteById(user.getId());

            verify(userRepository, times(1))
                    .findById(user.getId());
            verify(userRepository, times(1))
                    .deleteById(user.getId());
        }

        @Test
        @DisplayName("Should throw exception when not finding the user")
        void ShouldThrowExceptionWhenNotFindingTheUser() {
            doReturn(Optional.empty())
                    .when(userRepository)
                    .findById(user.getId());

            assertThrows(UserNotFoundException.class,
                    () -> userServiceImpl.deleteById(user.getId()));

            verify(userRepository, times(1))
                    .findById(user.getId());
            verify(userRepository, times(0))
                    .save(user);
        }
    }

    @Nested
    @DisplayName("Must log in successfully")
    class Login {

        User user = new User("Marco", "marco@gmail.com", "1234", "2637232314311"
                , UserRole.USER);

        LoginUserDTO loginUserDTO = new LoginUserDTO("marco@gmail.com", "1234");

        Algorithm algorithm = Algorithm.HMAC256("mock secret");
        String token = JWT.create()
                .withIssuer("auth-api")
                .withSubject(user.getUsername())
                .withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")))
                .sign(algorithm);

        @Test
        void login() {

            doReturn(new UsernamePasswordAuthenticationToken(user, null))
                    .when(authenticationManager).authenticate(any());
            doReturn(token)
                    .when(tokenService).generateToken(user);

            var response = userServiceImpl.login(loginUserDTO);

            assertEquals(response, new LoginResponse(token));

            verify(authenticationManager, times(1))
                    .authenticate(any());
            verify(tokenService, times(1))
                    .generateToken(user);
        }

        @Test
        @DisplayName("Should throw exception for incorrect email or password")
        void ShouldThrowExceptionForIncorrectEmailOrPassword() {
            doReturn(null)
                    .when(authenticationManager).authenticate(any());

            assertThrows(IncorrectEmailOrPassword.class,
                    () -> userServiceImpl.login(loginUserDTO));

            verify(authenticationManager, times(1))
                    .authenticate(any());
            verify(tokenService, times(0))
                    .generateToken(user);
        }
    }

    @Nested
    class findById {

        User user = new User("Marco", "marco@gmail.com", "1234", "2637232314311"
                , UserRole.USER);
        @Test
        @DisplayName("The user must be returned successfully")
        void TheUserMustBeReturnedSuccessfully() {
            doReturn(Optional.of(user))
                    .when(userRepository)
                    .findById(user.getId());

            var response = userServiceImpl.findById(user.getId());

            assertNotNull(response);
        }

        @Test
        @DisplayName("Should throw exception when not finding the user")
        void ShouldThrowExceptionWhenNotFindingTheUser() {
            doReturn(Optional.empty())
                    .when(userRepository)
                    .findById(user.getId());

            assertThrows(UserNotFoundException.class,
                    () -> userServiceImpl.findById(user.getId()));
        }
    }

    @Nested
    class updateUser {

        User user = new User("Marco", "marco@gmail.com", "1234", "2637232314311"
                , UserRole.USER);

        UpdateUserDTO updateUserDTO = new UpdateUserDTO(14L, "Pablo Alcantara", "163517317313");

        @Test
        @DisplayName("Must update basic user information")
        void MustUpdateBasicUserInformation() {
            doReturn(Optional.of(user))
                    .when(userRepository)
                    .findById(updateUserDTO.id());
            doReturn(user)
                    .when(userRepository)
                    .save(userArgumentCaptor.capture());


            userServiceImpl.updateUser(updateUserDTO);

            var userCaptured = userArgumentCaptor.getValue();

            assertEquals(user.getId(), userCaptured.getId());
            assertEquals(updateUserDTO.name(), userCaptured.getName());
            assertEquals(updateUserDTO.cpf(), userCaptured.getCpf());

            verify(userRepository, times(1))
                    .findById(updateUserDTO.id());
            verify(userRepository, times(1))
                    .save(user);
        }
        @Test
        @DisplayName("Should throw exception when not finding the user")
        void ShouldThrowExceptionWhenNotFindingTheUser() {
            doReturn(Optional.empty())
                    .when(userRepository)
                    .findById(updateUserDTO.id());

            assertThrows(UserNotFoundException.class,
                    () -> userServiceImpl.updateUser(updateUserDTO));

            verify(userRepository, times(1))
                    .findById(updateUserDTO.id());
            verify(userRepository, times(0))
                    .save(user);
        }
    }

    @Nested
    class ConfirmationCode {

        String code = RandomString.generateRandomString(6);

        User user = new User("Marco", "marco@gmail.com", "1234", "2637232314311"
                , UserRole.USER);

        @Test
        @DisplayName("Should confirm the provided code")
        void confirmationCode() throws MessagingException, UnsupportedEncodingException {
            doReturn(code)
                    .when(emailService).confirmationEmail(user);

            userServiceImpl.confirmationCode(user, code);

            verify(emailService, times(1))
                    .confirmationEmail(user);
        }
        @Test
        @DisplayName("Should throw exception for incorrect confirmation code")
        void ShouldThrowExceptionForIncorrectConfirmationCode() throws MessagingException, UnsupportedEncodingException {
            doReturn(code)
                    .when(emailService).confirmationEmail(user);

            assertThrows(IncorrectConfirmationCode.class,
                    () -> userServiceImpl.confirmationCode(user, "26152121"));

            verify(emailService, times(1))
                    .confirmationEmail(user);
        }
    }

    @Nested
    class EncryptedPassword {

        User user = new User("Marco", "marco@gmail.com", "1234", "2637232314311"
                , UserRole.USER);
        @Test
        @DisplayName("Should encrypt the user's password")
        void encryptedPassword() {
            String hash = passwordEncoder.encode(user.getPassword());

            doReturn(hash)
                    .when(passwordEncoder).encode(user.getPassword());

            userServiceImpl.encryptedPassword(user.getPassword());
        }
    }
}