package ecom.Services.User;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ecom.Configs.JWT.JwtProvider;
import ecom.DTO.Auth.AuthDTO;
import ecom.DTO.Auth.LoginDTO;
import ecom.Helper.Handler.Exceptions.ConflictException;
import ecom.Helper.Handler.Exceptions.UnauthorizedException;
import ecom.Models.CartModel;
import ecom.Models.USER_ROLE;
import ecom.Models.UserModel;

import ecom.Repositories.UserRepository;
import ecom.Repositories.Cart.CartRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private JwtProvider JwtProvider;
    
    @Autowired
    private CustomerUserService customerUserService;

    public AuthDTO register(UserModel user) throws Exception {

        //Get email register
        String email= user.getEmail();
            
        UserModel findUser = userRepository.findByEmail(email);
        System.out.println(findUser);
            if (findUser != null) {
                throw new ConflictException("Mail is already used with another account");
            }

            // Password encoder
            String passWordEncoded = passwordEncoder.encode(user.getPassword());
            user.setPassword(passWordEncoded);

            // save user
            UserModel savedUser = userRepository.save(user);
            

            CartModel cart = new CartModel();
            cart.setCustomer(savedUser);
            cartRepository.save(cart);

            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = JwtProvider.generateToken(authentication);

            AuthDTO authResponse = new AuthDTO();
            authResponse.setJwt(jwt);
            authResponse.setRole(user.getRole());

            return authResponse;
    }

    public AuthDTO LoginInByEmail(LoginDTO login) throws Exception {
            Authentication authentication = authentication(login.getEmail(), login.getPassword());

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            String role = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();

            // Generate token
            String jwt = JwtProvider.generateToken(authentication);

            AuthDTO authResponse = new AuthDTO();
            authResponse.setJwt(jwt);
            authResponse.setRole(USER_ROLE.valueOf(role));

            return authResponse;
    }

    public Authentication authentication(String email, String password) {

        UserDetails userDetails = customerUserService.loadUserByUsername(email);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new UnauthorizedException("Password incorrect");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
