package com.social.LongInstagram.sercurity;


import com.social.LongInstagram.config.SecurityContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;

@Service
public class JwtTokenProvider {
    public JwtTokenClaims getClaimsFromToken (String token){
        SecretKey key = Keys.hmacShaKeyFor(SecurityContext.SIGNKEY_KEY.getBytes());
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        String email  = String.valueOf(claims.get("email"));
        JwtTokenClaims jwtTokenClaims = new JwtTokenClaims();
        jwtTokenClaims.setEmail(email);
        return jwtTokenClaims;
    }
}
