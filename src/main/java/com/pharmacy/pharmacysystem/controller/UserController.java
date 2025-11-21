package com.pharmacy.pharmacysystem.controller;

import com.pharmacy.pharmacysystem.entity.User;
import com.pharmacy.pharmacysystem.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id,
                           @RequestBody User updated) {

        User existing = userService.getUserById(id);
        if (existing == null) {
            return null;
        }

        existing.setUsername(updated.getUsername());
        existing.setPassword(updated.getPassword());
        existing.setRole(updated.getRole());

        return userService.saveUser(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
