package com.example.ktspring.controllers;

import com.example.ktspring.repositories.UserRepository;
import com.example.ktspring.services.UserService;
import com.example.ktspring.vos.User;
import com.example.ktspring.vos.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Objects;

//aplicação restful aceita por definição tem uma arquitetura de servidor cliente e
// manipula os dados atraves de
// requisição http. tem que ser stateless nao salvar nada nela.
//annotation Restcontroller é pra dizer pra o spring que essa aplicação pro spring gerenciar.
//
//resquest mapping vai avisar pra o spring qual que é a url que vai bater passa um parametro.

@RestController
@RequestMapping("/user")

public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }
    // ResponseEntity objeto do spring usado pra retornar requisições http.
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody final User user){

        final var userResponse = userService.createUser(user);
       if (Objects.isNull(userResponse)){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
       return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
    @PostMapping("/createusers")
    public ResponseEntity<List<User>> createUsers(@RequestBody final List<User> users){

        final var userResponses = userService.createUsers(users);
        if (Objects.isNull(userResponses)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userResponses, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers(){
        List<UserResponse> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping(value = "/{email}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String email){
        final UserResponse userResponse = userService.getUser(email);
        if (Objects.isNull(userResponse)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @RequestBody User user){
        final UserResponse userResponse = userService.updateUser(id, user);
        if (Objects.isNull(userResponse)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable String id, @RequestBody User user){
        final UserResponse userResponse = userService.deleteUser(id, user);
        if(Objects.isNull(userResponse)){
            return  new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(userResponse,HttpStatus.BAD_REQUEST);
    }

}
