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

    @Override
    public List<UserModel> findAllUser() {
      return userRepository.findAll();
    }

  @Override
    public  UserModel findByJwtToken(String jwt) {
       String mail = jwtProvider.getMailFromJwtToken(jwt);
       UserModel user = findByMail(mail);
       return user;
    }

  @Override
  public UserModel findByMail(String mail) {
    UserModel user = userRepository.findByMail(mail);

    if (user == null) {
        throw new NotFoundException("User Not Found");
    }
    
    return user;

  } 
}
