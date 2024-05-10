package ecom.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

import ecom.Helper.Handler.Exceptions.NotFoundException;
import ecom.Models.USER_ROLE;
import ecom.Models.UserModel;
import ecom.Repositories.UserRepository;

@Service

public class CustomerUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        UserModel userModel = userRepository.findByMail(username);
        if (userModel == null) {
            throw new NotFoundException("User not found with mail: "+username);
        }

        USER_ROLE role =userModel.getRole();

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.toString()));
        return new User(userModel.getMail(), userModel.getPassword(), authorities);
        
    }

}
