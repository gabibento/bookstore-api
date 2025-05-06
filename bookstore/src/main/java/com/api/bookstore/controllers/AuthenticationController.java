package com.api.bookstore.controllers;

import com.api.bookstore.dtos.AuthenticationRequestDTO;
import com.api.bookstore.dtos.AuthenticationResponseDTO;
import com.api.bookstore.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody AuthenticationRequestDTO request){
        AuthenticationResponseDTO response = authenticationService.authenticate(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDTO> register(@RequestBody AuthenticationRequestDTO request){
        AuthenticationResponseDTO response = authenticationService.register(request);
        return ResponseEntity.ok(response);
    }
}
