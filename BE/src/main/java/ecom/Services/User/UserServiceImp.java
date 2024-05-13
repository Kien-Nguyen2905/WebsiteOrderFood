package ecom.Services.User;

import java.util.List;

import ecom.Models.UserModel;

public interface UserServiceImp {
public UserModel findByJwtToken(String jwt);
public List<UserModel> findAllUser();
public UserModel findByEmail(String mail);
}
