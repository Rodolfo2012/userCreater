package com.example.ktspring.converters;

import com.example.ktspring.vos.User;
import com.example.ktspring.vos.UserEntity;
import com.example.ktspring.vos.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserResponse userToUserResponse(User user){
        UserResponse userResponse = new UserResponse();

        userResponse.setEmail(user.getEmail());
        userResponse.setNome(user.getNome());
        userResponse.setId(user.getId());

        return userResponse;
    }

    public UserEntity userToUserEntity(User user){
        UserEntity userEntity = new UserEntity();

        userEntity.setNome(user.getNome());
        userEntity.setSenha(user.getSenha());
        userEntity.setEmail(user.getEmail());
        userEntity.setId(user.getId());

        return userEntity;
    }

    public UserResponse userResponseToUserEntity(User user){
        UserResponse userResponse = new UserResponse();

        userResponse.setNome(user.getNome());
        userResponse.setEmail(user.getEmail());
        userResponse.setId(user.getId());

        return userResponse;
    }
}
