package com.tech_raj.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtUtils {

    private static final long EXPIRATION_TIME = 1000 * 60 * 24 * 7 ; // 7 days

    private final SecretKey key;

    public JwtUtils(){
        String secretString="9vF2K8qPz1YwJmL4cR7sX0aD3hB6N5uTgQeZxCvWb8M=123sd3r4esdf4sdf456dfg56dsgvsd";
         byte[] decode = Base64.getDecoder().decode(secretString);
         this.key = new SecretKeySpec(decode, "HmacSHA256");
    }

    // generate Jwt Token
    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token){
        return  extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> function){
        return  function.apply(Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody());
    }

    public boolean isValidToken(String token,UserDetails userDetails){
        final String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenValid(token));
    }

    private boolean isTokenValid(String token){
        return extractClaims(token,Claims::getExpiration).before(new Date());
    }
}
