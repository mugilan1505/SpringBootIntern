package com.example.springbootfirst.controllers;

import com.example.springbootfirst.jwt.JwtResponseDto;
import com.example.springbootfirst.models.LoginDetailsDto;
import com.example.springbootfirst.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody LoginDetailsDto loginRequest) {
        JwtResponseDto response = registerService.authenticateAndGenerateToken(
                loginRequest.getUsername(), loginRequest.getPassword());

        return ResponseEntity.ok(response);
    }
}
