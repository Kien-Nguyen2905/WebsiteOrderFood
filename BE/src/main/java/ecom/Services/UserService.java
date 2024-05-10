package ecom.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecom.Models.UserModel;
import ecom.Repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserModel> findAllUser() {
      return userRepository.findAll();
    }

    public void signUp(UserModel newUser){
        
    } 
}
