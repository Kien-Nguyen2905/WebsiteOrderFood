package ecom.Controller;

import org.springframework.web.bind.annotation.RestController;

import ecom.Models.UserModel;
import ecom.Services.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/user")


public class UserController {
 @Autowired
    private  UserService userService;

    @GetMapping("/getAll")
    public List<UserModel> getAll() {
        return userService.findAllUser();
    }
}
