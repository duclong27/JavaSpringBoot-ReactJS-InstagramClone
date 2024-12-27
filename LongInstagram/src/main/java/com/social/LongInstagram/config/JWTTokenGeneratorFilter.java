package com.social.LongInstagram.config;



import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class JWTTokenGeneratorFilter {

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Kiểm tra xem người dùng đã được xác thực chưa
        if(authentication != null){
            System.out.println(authentication +"đx dc xác thực chưa");
            // Tạo JWT nếu có thông tin authentication
            SecretKey key = Keys.hmacShaKeyFor(SecurityContext.SIGNKEY_KEY.getBytes());
            String jwt = Jwts.builder()
                    .setIssuer("instagram") // Thêm thông tin phát hành token
                    .setIssuedAt(new Date()) // Thời gian tạo token
                    .claim("authorities", populateAuthoities(authentication.getAuthorities())) // Thêm quyền của người dùng
                    .claim("email", authentication.getName()) // Thêm tên người dùng (hoặc email)
                    .setExpiration(new Date(new Date().getTime() + 3000000)) // Thời gian hết hạn (5 phút)
                    .signWith(key) // Ký token với khóa bí mật
                    .compact();
            // Thêm JWT vào header của response
            response.setHeader("Authorization", "Bearer " + jwt);
        }
        // Tiếp tục chuỗi bộ lọc
        filterChain.doFilter(request, response);
    }

    private String populateAuthoities(Collection<? extends GrantedAuthority> authorities) {
        // Biến đổi danh sách quyền thành chuỗi, ngăn cách bằng dấu phẩy
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }

    protected boolean shouldNotFilter(HttpServletRequest req) throws ServletException {
        return !req.getServletPath().equals("/signin");  // Bộ lọc sẽ được áp dụng nếu đường dẫn là /signin
    }


}
