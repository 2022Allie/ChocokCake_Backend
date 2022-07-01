package com.example.chocokcake.security;


import com.example.chocokcake.security.auth.AuthDetailsService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    @Value("${SECRET}")
    private String JWT_SECRET;
    private final AuthDetailsService authDetailsService;
    public String getBearerToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken == null){
            return null;
        }
        return bearerToken.substring(7);
    }
    public String getUserId(String token){
        try{
            return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().getSubject();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    public String generateAccessToken(String accountId){
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 720000000))
                .setSubject(accountId)
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();
    }
    public Authentication getAuthentication(String token){
        UserDetails userDetails = authDetailsService.loadUserByUsername(getUserId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", null);
    }

}
