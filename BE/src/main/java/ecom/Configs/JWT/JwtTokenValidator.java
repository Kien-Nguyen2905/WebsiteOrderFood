package ecom.Configs.JWT;

import java.io.IOException;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;

public class JwtTokenValidator extends OncePerRequestFilter {

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String jwt = request.getHeader(JwtConstant.JWT_HEADER);
                if(jwt !=null){
                    jwt = jwt.substring(7);

                    try {
                        
                        SecretKey secretKey = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
                        @SuppressWarnings("deprecation")
                        Claims clamis = Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody();
                        String mail = String.valueOf(clamis.get("mail"));
                        String authorities = String.valueOf((clamis.get("authorities")));

                        //ROLE_CUSTOMER, ROLE_ADMIN
                        List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
                        Authentication authentication = new UsernamePasswordAuthenticationToken(mail,null, auth);
                        SecurityContextHolder.getContext().setAuthentication(authentication);

                    } catch (Exception e) {
                        throw new BadCredentialsException("Invalid Token");
                    }
                }

                filterChain.doFilter(request, response);
    }

}
