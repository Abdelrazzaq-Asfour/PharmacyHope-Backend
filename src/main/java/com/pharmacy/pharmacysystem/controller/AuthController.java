package com.pharmacy.pharmacysystem.controller;

import com.pharmacy.pharmacysystem.dto.LoginRequest;
import com.pharmacy.pharmacysystem.entity.Admin;
import com.pharmacy.pharmacysystem.entity.User;
import com.pharmacy.pharmacysystem.repository.AdminRepository;
import com.pharmacy.pharmacysystem.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:3000"})
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    public AuthController(UserRepository userRepository,
                          AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {

        Admin admin = adminRepository
                .findByUsernameAndPassword(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
                .orElse(null);

        if (admin != null) {
            AuthResponse resp = new AuthResponse(
                    "Admin login successful",
                    admin.getUsername(),
                    "ADMIN"
            );
            return ResponseEntity.ok(resp);
        }

        User user = userRepository
                .findByUsernameAndPassword(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
                .orElse(null);

        if (user != null) {
            AuthResponse resp = new AuthResponse(
                    "User login successful",
                    user.getUsername(),
                    "USER"
            );
            return ResponseEntity.ok(resp);
        }

        AuthResponse resp = new AuthResponse(
                "Invalid username or password",
                null,
                null
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
    }


    @PostMapping("/user-login")
    public ResponseEntity<AuthResponse> userLogin(@RequestBody LoginRequest loginRequest) {

        var user = userRepository
                .findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword())
                .orElse(null);

        if (user != null) {
            return ResponseEntity.ok(
                    new AuthResponse("User login successful", user.getUsername(), "USER")
            );
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new AuthResponse("Invalid user credentials", null, null)
        );
    }


}
