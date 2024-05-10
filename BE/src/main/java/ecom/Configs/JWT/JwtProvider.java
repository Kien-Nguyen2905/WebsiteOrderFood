package ecom.Configs.JWT;

import javax.crypto.SecretKey;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {
    private SecretKey secretKey = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
    @SuppressWarnings("deprecation")
    public String generateToken(Authentication authentication){
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String roles = populateAuthorities(authorities);

        String jwt = Jwts.builder().setIssuedAt(new Date()).setExpiration((new Date(new Date().getTime()+86400000)))
                                .claim("mail",authentication.getName())
                                .claim("authorities",roles)
                                .signWith(secretKey)
                                .compact();
        
        return jwt;
    }

    @SuppressWarnings("deprecation")
    public String getMailFromJwtToken(String jwt){

          Claims clamis = Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody();
                        String mail = String.valueOf(clamis.get("mail"));
                        // String authorities = String.valueOf((clamis.get("authorities")));
                        return mail;

    }

    private String populateAuthorities(Collection <? extends GrantedAuthority> authorities) {
        Set<String> auths = new HashSet<>();
        for(GrantedAuthority authority:authorities){
            auths.add(authority.getAuthority());
        }
        return String.join(",", auths);

    }
}
