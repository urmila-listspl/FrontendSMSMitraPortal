package com.list20.SMSMitraPortal.service;


import com.list20.SMSMitraPortal.pojo.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(User user);

    boolean isTokenValid(String token, UserDetails userDetails);

    boolean expireJWT(String token);
}
