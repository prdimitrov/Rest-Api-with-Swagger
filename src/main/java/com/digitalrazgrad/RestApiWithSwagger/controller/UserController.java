package com.digitalrazgrad.RestApiWithSwagger.controller;

import com.digitalrazgrad.RestApiWithSwagger.model.entity.User;
import com.digitalrazgrad.RestApiWithSwagger.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allusers")
    public Set<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
//        User newUser = userService.addUser(user);
        return ResponseEntity.ok(userService.addUser(user));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long userId, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(userId, user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
