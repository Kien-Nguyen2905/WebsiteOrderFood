package ecom.Controller.User;


import ecom.Helper.Handler.ResponseHandler;
import ecom.Models.UserModel;
import ecom.Services.User.UserService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;




@RestController
@RequestMapping("/api/admin")
public class UserController {
    @Autowired
    private  UserService userService;

    @GetMapping("/user")
    public ResponseEntity<Object> signUp(@RequestHeader("Authorization")  String jwt) throws Exception {
        if (jwt != null) {
            jwt = jwt.substring(7);
        }
        UserModel user = userService.findByJwtToken(jwt);

        return ResponseHandler.success("success", HttpStatus.OK, user) ;
    }
    
}
