package com.api.bookstore.services;

import com.api.bookstore.dtos.AuthenticationRequestDTO;
import com.api.bookstore.dtos.AuthenticationResponseDTO;
import com.api.bookstore.entities.Role;
import com.api.bookstore.entities.User;
import com.api.bookstore.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }
    public AuthenticationResponseDTO register(AuthenticationRequestDTO request){
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("User already registered");
        }

        userRepository.save(new User(request.getUsername(),
                new BCryptPasswordEncoder().encode(request.getPassword()),
                Role.ROLE_USER));
        return authenticate(request);
    }
    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),
                            request.getPassword())
            );
            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

            String token = jwtService.generateToken(user);

            return new AuthenticationResponseDTO(token);
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid username or password");
        }
    }
}
