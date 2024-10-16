package com.bandeira.sistema_venda_de_ingressos.services.impl;

import com.bandeira.sistema_venda_de_ingressos.dtos.CreatePlanDTO;
import com.bandeira.sistema_venda_de_ingressos.dtos.UpdatePlanDTO;
import com.bandeira.sistema_venda_de_ingressos.dtos.UpdateUserDTO;
import com.bandeira.sistema_venda_de_ingressos.exceptions.PlanNotFoundException;
import com.bandeira.sistema_venda_de_ingressos.exceptions.UserNotFoundException;
import com.bandeira.sistema_venda_de_ingressos.models.Plan;
import com.bandeira.sistema_venda_de_ingressos.models.User;
import com.bandeira.sistema_venda_de_ingressos.models.enums.UserRole;
import com.bandeira.sistema_venda_de_ingressos.repositories.PlanRepository;
import com.bandeira.sistema_venda_de_ingressos.repositories.UserRepository;
import com.bandeira.sistema_venda_de_ingressos.services.EmailService;
import com.bandeira.sistema_venda_de_ingressos.services.VipPlanService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VipPlanServiceImplTest {

    @InjectMocks
    VipPlanServiceImpl vipPlanServiceImpl;

    @Mock
    UserRepository userRepository;

    @Mock
    PlanRepository planRepository;

    @Mock
    EmailService emailService;

    @Captor
    ArgumentCaptor<Plan> planArgumentCaptor;

    @Nested
    class BuySilverPlan {

        User user = new User("Marco", "marco@gmail.com", "1234", "2637232314311"
                , UserRole.USER);

        Plan plan = new Plan(1L ,"Minha vida", BigDecimal.valueOf(94.63));

        @Test
        @DisplayName("Should successfully buy the silver plan and update user points")
        void buySilverPlan() throws MessagingException, UnsupportedEncodingException {
            doReturn(Optional.of(user))
                    .when(userRepository).findById(user.getId());
            doReturn(Optional.of(plan)).when(planRepository)
                    .findById(1L);

            vipPlanServiceImpl.buySilverPlan(user.getId());

            assertEquals(user.getPoints(), 2L);

            verify(userRepository, times(1))
                    .findById(user.getId());
            verify(planRepository, times(1))
                    .findById(1L);
        }

        @Test
        @DisplayName("Should throw exception when user is not found")
        void ShouldThrowExceptionWhenUserIsNotFound(){
            doReturn(Optional.empty())
                    .when(userRepository).findById(user.getId());

            user.setPoints(0L);

            assertThrows(UserNotFoundException.class,
                    () ->vipPlanServiceImpl.buySilverPlan(user.getId()));

            assertEquals(user.getPoints(), 0L);

            verify(userRepository, times(1))
                    .findById(user.getId());
            verify(planRepository, times(0))
                    .findById(1L);
        }

        @Test
        @DisplayName("Should throw exception when plan is not found")
        void ShouldThrowExceptionWhenPlanIsNotFound(){
            doReturn(Optional.of(user))
                    .when(userRepository).findById(user.getId());
            doReturn(Optional.empty()).when(planRepository)
                    .findById(1L);

            user.setPoints(0L);

            assertThrows(PlanNotFoundException.class,
                    () ->vipPlanServiceImpl.buySilverPlan(user.getId()));

            assertEquals(user.getPoints(), 0L);

            verify(userRepository, times(1))
                    .findById(user.getId());
            verify(planRepository, times(1))
                    .findById(1L);
        }
    }

    @Nested
    class BuyGoldPlan {
        User user = new User("Marco", "marco@gmail.com", "1234", "2637232314311"
                , UserRole.USER);

        Plan plan = new Plan(1L ,"Minha vida", BigDecimal.valueOf(94.63));

        @Test
        @DisplayName("Should successfully buy the gold plan and update user points")
        void buyGoldPlan() throws MessagingException, UnsupportedEncodingException {
            doReturn(Optional.of(user))
                    .when(userRepository).findById(user.getId());
            doReturn(Optional.of(plan)).when(planRepository)
                    .findById(2L);

            vipPlanServiceImpl.buyGoldPlan(user.getId());

            assertEquals(user.getPoints(), 4L);

            verify(userRepository, times(1))
                    .findById(user.getId());
            verify(planRepository, times(1))
                    .findById(2L);
        }

        @Test
        @DisplayName("Should throw exception when user is not found")
        void ShouldThrowExceptionWhenUserIsNotFound(){
            doReturn(Optional.empty())
                    .when(userRepository).findById(user.getId());

            user.setPoints(0L);

            assertThrows(UserNotFoundException.class,
                    () ->vipPlanServiceImpl.buyGoldPlan(user.getId()));

            assertEquals(user.getPoints(), 0L);

            verify(userRepository, times(1))
                    .findById(user.getId());
            verify(planRepository, times(0))
                    .findById(2L);
        }

        @Test
        @DisplayName("Should throw exception when plan is not found")
        void ShouldThrowExceptionWhenPlanIsNotFound(){
            doReturn(Optional.of(user))
                    .when(userRepository).findById(user.getId());
            doReturn(Optional.empty()).when(planRepository)
                    .findById(2L);

            user.setPoints(0L);

            assertThrows(PlanNotFoundException.class,
                    () ->vipPlanServiceImpl.buyGoldPlan(user.getId()));

            assertEquals(user.getPoints(), 0L);

            verify(userRepository, times(1))
                    .findById(user.getId());
            verify(planRepository, times(1))
                    .findById(2L);
        }
    }

    @Nested
    class BuyEmeraldPlan{

        User user = new User("Marco", "marco@gmail.com", "1234", "2637232314311"
                , UserRole.USER);

        Plan plan = new Plan(1L ,"Minha vida", BigDecimal.valueOf(94.63));

        @Test
        @DisplayName("Should successfully buy the emerald plan and update user points")
        void buyEmeraldPlan() throws MessagingException, UnsupportedEncodingException {
            doReturn(Optional.of(user))
                    .when(userRepository).findById(user.getId());
            doReturn(Optional.of(plan)).when(planRepository)
                    .findById(3L);

            vipPlanServiceImpl.buyEmeraldPlan(user.getId());

            assertEquals(user.getPoints(), 6L);

            verify(userRepository, times(1))
                    .findById(user.getId());
            verify(planRepository, times(1))
                    .findById(3L);
        }
        @Test
        @DisplayName("Should throw exception when plan is not found")
        void ShouldThrowExceptionWhenPlanIsNotFound(){
            doReturn(Optional.empty())
                    .when(userRepository).findById(user.getId());

            user.setPoints(0L);

            assertThrows(UserNotFoundException.class,
                    () ->vipPlanServiceImpl.buyEmeraldPlan(user.getId()));

            assertEquals(user.getPoints(), 0L);

            verify(userRepository, times(1))
                    .findById(user.getId());
            verify(planRepository, times(0))
                    .findById(3L);
        }

        @Test
        @DisplayName("Should throw exception when user is not found")
        void ShouldThrowExceptionWhenUserIsNotFound(){
            doReturn(Optional.of(user))
                    .when(userRepository).findById(user.getId());
            doReturn(Optional.empty()).when(planRepository)
                    .findById(3L);

            user.setPoints(0L);

            assertThrows(PlanNotFoundException.class,
                    () ->vipPlanServiceImpl.buyEmeraldPlan(user.getId()));

            assertEquals(user.getPoints(), 0L);

            verify(userRepository, times(1))
                    .findById(user.getId());
            verify(planRepository, times(1))
                    .findById(3L);
        }
    }

    @Nested
    class ShouldThrowExceptionWhenPlanIsNotFound{
        User user = new User("Marco", "marco@gmail.com", "1234", "2637232314311"
                , UserRole.USER);

        Plan plan = new Plan(1L ,"Minha vida", BigDecimal.valueOf(94.63));

        @Test
        @DisplayName("Should successfully buy the diamond plan and update user points")
        void buyDiamondPlan() throws MessagingException, UnsupportedEncodingException {
            doReturn(Optional.of(user))
                    .when(userRepository).findById(user.getId());
            doReturn(Optional.of(plan)).when(planRepository)
                    .findById(4L);

            vipPlanServiceImpl.buyDiamondPlan(user.getId());

            assertEquals(user.getPoints(), 10L);

            verify(userRepository, times(1))
                    .findById(user.getId());
            verify(planRepository, times(1))
                    .findById(4L);
        }

        @Test
        @DisplayName("Should throw exception when user is not found")
        void ShouldThrowExceptionWhenIsNotFound(){
            doReturn(Optional.empty())
                    .when(userRepository).findById(user.getId());

            user.setPoints(0L);

            assertThrows(UserNotFoundException.class,
                    () ->vipPlanServiceImpl.buyDiamondPlan(user.getId()));

            assertEquals(user.getPoints(), 0L);

            verify(userRepository, times(1))
                    .findById(user.getId());
            verify(planRepository, times(0))
                    .findById(4L);
        }

        @Test
        @DisplayName("Should throw exception when user is not found")
        void ShouldThrowExceptionWhenUserIsNotFound(){
            doReturn(Optional.of(user))
                    .when(userRepository).findById(user.getId());
            doReturn(Optional.empty()).when(planRepository)
                    .findById(4L);

            user.setPoints(0L);

            assertThrows(PlanNotFoundException.class,
                    () ->vipPlanServiceImpl.buyDiamondPlan(user.getId()));

            assertEquals(user.getPoints(), 0L);

            verify(userRepository, times(1))
                    .findById(user.getId());
            verify(planRepository, times(1))
                    .findById(4L);
        }
    }

    @Nested
    class CreatePlan {

        Plan plan = new Plan(1L ,"Minha vida", BigDecimal.valueOf(94.63));

        CreatePlanDTO createPlanDTO = new CreatePlanDTO("Aygdyagd", BigDecimal.valueOf(86.90));

        @Test
        @DisplayName("Should create a new plan successfully")
        void createPlan() {
            doReturn(plan).when(planRepository)
                    .save(planArgumentCaptor.capture());

            var response = vipPlanServiceImpl.createPlan(createPlanDTO);

            var planCaptured = planArgumentCaptor.getValue();

            assertEquals(response, plan);
            assertEquals(createPlanDTO.name(), planCaptured.getName());
            assertEquals(createPlanDTO.price(), planCaptured.getPrice());
        }
    }

    @Nested
    class deleteById {

        Plan plan = new Plan(1L ,"Minha vida", BigDecimal.valueOf(94.63));

        @Test
        @DisplayName("Must delete plan successfully")
        void MustDeletePlanSuccessfully() {
            doReturn(Optional.of(plan))
                    .when(planRepository)
                    .findById(plan.getId());
            doNothing()
                    .when(planRepository)
                    .deleteById(plan.getId());

            vipPlanServiceImpl.deleteById(plan.getId());

            verify(planRepository, times(1))
                    .findById(plan.getId());
            verify(planRepository, times(1))
                    .deleteById(plan.getId());
        }

        @Test
        @DisplayName("Should throw exception when not finding the plan")
        void ShouldThrowExceptionWhenNotFindingTheUPlan() {
            doReturn(Optional.empty())
                    .when(planRepository)
                    .findById(plan.getId());

            assertThrows(PlanNotFoundException.class,
                    () -> vipPlanServiceImpl.deleteById(plan.getId()));

            verify(planRepository, times(1))
                    .findById(plan.getId());
            verify(planRepository, times(0))
                    .save(plan);
        }
    }

    @Nested
    class updateUser {

        Plan plan = new Plan(1L ,"Minha vida", BigDecimal.valueOf(94.63));

        UpdatePlanDTO updatePlanDTO = new UpdatePlanDTO(8L, "Cartinhlha" , BigDecimal.valueOf(133.00));

        @Test
        @DisplayName("Should update the plan information successfully")
        void ShouldUpdatePlanSuccessfully() {
            doReturn(Optional.of(plan))
                    .when(planRepository)
                    .findById(updatePlanDTO.id());
            doReturn(plan)
                    .when(planRepository)
                    .save(planArgumentCaptor.capture());


            vipPlanServiceImpl.updatePlan(updatePlanDTO);

            var planCaptured = planArgumentCaptor.getValue();

            assertEquals(plan.getId(), planCaptured.getId());
            assertEquals(updatePlanDTO.name(), planCaptured.getName());
            assertEquals(updatePlanDTO.price(), planCaptured.getPrice());

            verify(planRepository, times(1))
                    .findById(updatePlanDTO.id());
            verify(planRepository, times(1))
                    .save(plan);
        }

        @Test
        @DisplayName("Should throw exception when not finding the user")
        void ShouldThrowExceptionWhenNotFindingTheUser() {
            doReturn(Optional.empty())
                    .when(planRepository)
                    .findById(updatePlanDTO.id());

            assertThrows(PlanNotFoundException.class,
                    () -> vipPlanServiceImpl.updatePlan(updatePlanDTO));

            verify(planRepository, times(1))
                    .findById(updatePlanDTO.id());
            verify(planRepository, times(0))
                    .save(plan);
        }
    }
}