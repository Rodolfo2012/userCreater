package com.example.ktspring.services;

import com.example.ktspring.converters.UserConverter;
import com.example.ktspring.repositories.UserRepository;
import com.example.ktspring.vos.User;
import com.example.ktspring.vos.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter converter;

    @Autowired
    public UserService(final UserRepository userRepository, final UserConverter converter) {
        this.userRepository = userRepository;
        this.converter = converter;
    }

    public User createUser(final User userRequest) {

        User createdUser = null;

        List<User> users = userRepository.getUsers();

        List<User> usersRepeatedEmails = users.stream()
                .filter(user -> user.getEmail().equals(userRequest.getEmail())).toList();
        if (usersRepeatedEmails.isEmpty()){
            User beCreated = new User();
            beCreated.setEmail(userRequest.getEmail());
           beCreated.setSenha(userRequest.getSenha());
           beCreated.setNome(userRequest.getNome());

           String idCombined = beCreated.getEmail() + beCreated.getSenha();
           String id = String.valueOf(idCombined.hashCode());
           beCreated.setId(id);
            createdUser = userRepository.createUser(beCreated);

        }
        return  createdUser;
    }
    public List<UserResponse> getUsers(){
        List<UserResponse> userResponses = new ArrayList<>();
        List<User> users = userRepository.getUsers();
        users.forEach(user -> userResponses.add(converter.userToUserResponse(user)));
        return userResponses;
    }
    public UserResponse getUser(String email){
        List<User> users = userRepository.getUsers();
        var user1 = users.stream().filter(user -> user.getEmail().equals(email)).findFirst().get();
        var userResponse = converter.userToUserResponse(user1);
        return userResponse;
    }

    public UserResponse updateUser(String id, User user) {
        UserResponse userResponse = userRepository.updateUser(id, user);
        return userResponse;

    }

    public UserResponse deleteUser(String id, User user) {
        UserResponse userResponse = userRepository.deleteUser(id, user);
        return userResponse;
    }
   // hasequal hasset
    public List<User> createUsers(List<User> users) {
        User newUser = null;

        List<User> users1 = new ArrayList<>();
        List<User> listUsers = userRepository.getUsers();

        List<User> repeatedUsers = listUsers.stream().filter(users::contains).toList();

        if (repeatedUsers.isEmpty()) {
            for (User user : users) {
                newUser = new User();
                newUser.setEmail(user.getEmail());
                newUser.setSenha(user.getSenha());
                newUser.setNome(user.getNome());
                String idCombined = newUser.getNome() + newUser.getSenha();
                String id = String.valueOf(idCombined.hashCode());
                newUser.setId(id);
                users1.add(newUser);

            }

        }
        List<User> users2 = userRepository.creatUsers(users1);

        return users2;
        }

}
