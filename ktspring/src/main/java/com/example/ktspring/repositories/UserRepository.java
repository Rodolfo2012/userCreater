package com.example.ktspring.repositories;
import com.example.ktspring.converters.UserConverter;

import com.example.ktspring.vos.User;
import com.example.ktspring.vos.UserResponse;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private final UserConverter converter;
    List<User> usersDB = new ArrayList<>();

    public List<User> getUsers(){
        return usersDB;
    }
    public List<User> creatUsers(List<User> users){
        usersDB.addAll(users);
        return users;
    }
    public User createUser(User user){
        usersDB.add(user);
        return user;
    }

    public UserRepository(final List<User> usersDB, UserConverter converter) {
        this.usersDB = usersDB;
        this.converter = converter;
    }

    public UserResponse updateUser(String id, User user) {
      User user2 = usersDB.stream().filter(user1 -> user1.getId().equals(id)).toList().get(0);
      usersDB.remove(user2);

      usersDB.add(user);

          UserResponse userResponse = converter.userToUserResponse(user);
          return userResponse;
    }

    public UserResponse deleteUser(String id, User user) {
        User user2 = usersDB.stream().filter(user1 -> user1.getId().equals(id)).toList().get(0);
        usersDB.remove(user2);



        UserResponse userResponse = converter.userToUserResponse(user2);

        return  userResponse;
    }

//    public User getUserById(String id){
//        User user1 = usersDB.stream().filter(user -> user.getId().equals(id)).toList().get(0);
//        return user1;
//    }
}
