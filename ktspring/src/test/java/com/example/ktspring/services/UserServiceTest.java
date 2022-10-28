package com.example.ktspring.services;

import com.example.ktspring.controllers.UserController;
import com.example.ktspring.converters.UserConverter;
import com.example.ktspring.repositories.UserRepository;
import com.example.ktspring.vos.User;
import com.example.ktspring.vos.UserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

class UserServiceTest {

   @InjectMocks
   private UserService userService;

   @Mock
   private UserRepository userRepository;

   @Mock
   private UserConverter userConverter;


    @Test
    void mustReturnCreatedUser() {


        User user = new User();
        user.setNome("Rodolfo");
        user.setSenha("Rodolfo2012");
        user.setEmail("rodolfo2012@gmail.com");

        Mockito.when(userRepository.getUsers()).thenReturn(new ArrayList<>());
        Mockito.when(userRepository.createUser(Mockito.any())).thenReturn(user);

     var createdUser = userService.createUser(user);

     assertThat(createdUser).isEqualTo(user);

    }


    @Test
    void getAllUsers() {
        List<User> userList1 = new ArrayList<>();

        User user = new User();
        user.setNome("jose");
        user.setEmail("jose2012@gmail.com");
        user.setSenha("jose2012");

        User user1 = new User();
        user.setNome("rodolfo");
        user.setEmail("rodolfo2012@gmail.com");
        user.setSenha("rodolfo2012");

        userList1.add(user);
        userList1.add(user1);

        Mockito.when(userRepository.getUsers()).thenReturn(userList1);

        var allUsers = userService.getUsers();
    }

    @Test
    void getUserbyEmail() {



        List<User> userList1 = new ArrayList<>();

        User user = new User();
        user.setNome("jose");
        user.setEmail("jose2012@gmail.com");
        user.setSenha("15487812314");


        userList1.add(user);

        UserResponse userResponse = new UserResponse();
        userResponse.setNome("jose");
        userResponse.setEmail("jose2012@gmail.com");
        userResponse.setId("548572012");



        Mockito.when(userRepository.getUsers()).thenReturn(userList1);
        Mockito.when(userConverter.userToUserResponse(Mockito.any())).thenReturn(userResponse);
        var result = userService.getUser("jose2012@gmail.com");

        assertThat(result.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setNome("jose");
        user.setEmail("jose2012@gmail.com");
        user.setSenha("15487812314");

        UserResponse userResponse = new UserResponse();
        userResponse.setNome("jose");
        userResponse.setId("15487812314");
        userResponse.setEmail("rodolfo2012@gmail.com");

        Mockito.when(userRepository.updateUser(Mockito.anyString(),Mockito.any())).thenReturn(userResponse);
        var result = userService.updateUser("15487812314", user);
    }

    @Test
    void deleteUser() {
        User user = new User();
        user.setNome("jose");
        user.setEmail("jose2012@gmail.com");
        user.setSenha("15487812314");

        UserResponse userResponse = new UserResponse();
        userResponse.setNome("jose");
        userResponse.setId("15487812314");
        userResponse.setEmail("jose2012@gmail.com");

        Mockito.when(userRepository.deleteUser(Mockito.anyString(),Mockito.any())).thenReturn(userResponse);

        var result = userService.deleteUser("15487812314", user);

        assertNotNull(result);


    }

    @Test
    void createUsers() {
        List<User> userList1 = new ArrayList<>();

        User user = new User();
        user.setNome("jose");
        user.setEmail("jose2012@gmail.com");
        user.setSenha("15487812314");

        User user1 = new User();
        user.setNome("rodolfo");
        user.setEmail("rodolfo2012@gmail.com");
        user.setSenha("57849469");

        userList1.add(user);
        userList1.add(user1);

        Mockito.when(userRepository.getUsers()).thenReturn(new ArrayList<>());
        Mockito.when(userRepository.creatUsers(Mockito.any())).thenReturn(userList1);

        var createdUsers = userService.createUsers(userList1);

        assertThat(createdUsers).isEqualTo(userList1);

    }
}