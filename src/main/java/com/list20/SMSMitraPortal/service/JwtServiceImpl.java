package com.list20.SMSMitraPortal.service;


import com.list20.SMSMitraPortal.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${secret.key}")
    private String secretKey;

    public JwtServiceImpl() {
    }

    public String generateToken(User user) {
        return Jwts.builder().setSubject(user.getUsername()).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(DateUtils.addDays(new Date(), 1)).signWith(this.getSignInKey(), SignatureAlgorithm.HS256).compact();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        Claims claims = this.extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return (Claims)Jwts.parserBuilder().setSigningKey(this.getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    public String extractUserName(String token) {
        return (String)this.extractClaim(token, Claims::getSubject);
    }

    private Key getSignInKey() {
        byte[] key = (byte[])Decoders.BASE64.decode(this.secretKey);
        return Keys.hmacShaKeyFor(key);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String userName = this.extractUserName(token);
        return userName.equals(userDetails.getUsername()) && !this.isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return ((Date)this.extractClaim(token, Claims::getExpiration)).before(new Date());
    }

    public boolean expireJWT(String token) {
        System.out.println("token-->" + token);
        Claims claims = this.extractAllClaims(token.replace("\"", ""));
        claims.setExpiration(new Date(System.currentTimeMillis()));
        Jwts.builder().setClaims(claims).signWith(this.getSignInKey()).compact();
        return this.isTokenExpired(token.replace("\"", ""));
    }
}

