package com.example.employeemanagement.Services;

import com.example.employeemanagement.Dto.AdminLoginDto;
import com.example.employeemanagement.Dto.employee.AuthResponse;
import com.example.employeemanagement.Security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class AdminService {

    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;
    @Autowired
    public AdminService(AuthenticationManager authenticationManager,
                         JWTGenerator jwtGenerator){
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
    }

    public ResponseEntity<AuthResponse> Login(AdminLoginDto adminLoginDto) {
            try{
                Authentication authentication = authenticationManager.authenticate
                        (new UsernamePasswordAuthenticationToken(adminLoginDto.getUsername(), adminLoginDto.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String token = jwtGenerator.generateJWT(authentication);
                AuthResponse authResponse = new AuthResponse(token);
                return new ResponseEntity<>(authResponse, HttpStatus.OK);
            }catch (Exception e) {
                throw new IllegalStateException("Something went wrong: "+ e.getMessage());
            }
    }
}
