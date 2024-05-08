package ecom.Services;

import java.util.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ecom.Configs.JWT.JwtProvider;
import ecom.DTO.AuthDTO;
import ecom.DTO.LoginDTO;
import ecom.DTO.UserDTO;
import ecom.Models.CartModel;
import ecom.Models.USER_ROLE;
import ecom.Models.UserModel;
import ecom.Repositories.CartRepository;
import ecom.Repositories.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private JwtProvider JwtProvider;
    @Autowired
    private CustomerUserService customerUserService;

    public AuthDTO register(UserDTO user) throws Exception {

        boolean findUser = userRepository.findByEmailOrPhone(user.getMail(), user.getPhone());

        if (findUser == true) {
            throw new Exception("mail or phone is already used with another account");
        }

        // password encoder
        String passWordEncoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(passWordEncoded);

        // mapper userDTO to userModel
        UserModel mapUser = modelMapper.map(user, UserModel.class);

        // save user
            UserModel newUser = userRepository.save(mapUser);
            CartModel cart = new CartModel();
            cart.setCustomer(newUser);  
            cartRepository.save(cart);

      
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getMail(), user.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = JwtProvider.generateToken(authentication);

            AuthDTO authResponse = new AuthDTO();
            authResponse.setJwt(jwt);
            authResponse.setRole(user.getRole());
    
            return authResponse;
    }

    public AuthDTO LoginInByMail(LoginDTO login) throws Exception {

        
        Authentication authentication = authentication(login.getMail(), login.getPassword());

        Collection< ? extends GrantedAuthority>  authorities = authentication.getAuthorities();
        String role = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();
        

        // generate token
        String jwt = JwtProvider.generateToken(authentication);

        AuthDTO authResponse = new AuthDTO();
        authResponse.setJwt(jwt);
        authResponse.setRole(USER_ROLE.valueOf(role));
        return authResponse;
    }
    public Authentication authentication(String mail, String password) {

        UserDetails userDetails = customerUserService.loadUserByUsername(mail);
    
        if (userDetails == null) {
            throw new BadCredentialsException("invalid mail");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("invalid password");
        }
    
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
