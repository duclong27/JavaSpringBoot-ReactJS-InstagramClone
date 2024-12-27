package com.social.LongInstagram.config;


import com.social.LongInstagram.entities.User;
import com.social.LongInstagram.exception.UserException;
import com.social.LongInstagram.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.management.IntrospectionException;
import java.io.IOException;
import java.security.Key;
import java.util.List;

@Component
@Getter
@Setter
public class JwtTokenValidationFilter extends OncePerRequestFilter  {

    @Autowired
    @Lazy
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader(SecurityContext.HEADER);

        if (jwt != null) {
            try {
                jwt = jwt.substring(7);  // Loại bỏ "Bearer " prefix
                SecretKey key = Keys.hmacShaKeyFor(SecurityContext.SIGNKEY_KEY.getBytes());
//                // Lấy thông tin từ claims
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(jwt).getBody(); // Nếu token hợp lệ, sẽ không ném exception
                String email = String.valueOf(claims.get("email"));  // Đổi thành "username" nếu bạn dùng email làm username
                String authorities = (String) claims.get("authorities");
//                 Chuyển authorities thành danh sách quyền
                List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
//                // Tạo đối tượng Authentication
                Authentication auth = new UsernamePasswordAuthenticationToken(email, null, auths);
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                throw new BadCredentialsException("Invalidated Token...");
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest req) throws ServletException {
        return !req.getServletPath().equals("/signin");  // Bộ lọc sẽ được áp dụng nếu đường dẫn là /signin
    }

}
