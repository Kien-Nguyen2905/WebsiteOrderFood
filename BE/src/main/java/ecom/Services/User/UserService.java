package ecom.Services.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecom.Configs.JWT.JwtProvider;
import ecom.Helper.Handler.Exceptions.NotFoundException;
import ecom.Models.UserModel;
import ecom.Repositories.UserRepository;

@Service
public class UserService implements UserServiceImp {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    //Get all user
    @Override
    public List<UserModel> findAllUser() {
      return userRepository.findAll();
    }

    //Get user by jwt token
  @Override
    public  UserModel findByJwtToken(String jwt) {
      
      if (jwt != null) {
        jwt = jwt.substring(7);
    }

       String mail = jwtProvider.getMailFromJwtToken(jwt);
       UserModel user = findByEmail(mail);
       return user;
    }

   //Get user by email
  @Override
  public UserModel findByEmail(String email) {
    UserModel user = userRepository.findByEmail(email);

    if (user == null) {
        throw new NotFoundException("User Not Found");
    }
    
    return user;

  } 
}
