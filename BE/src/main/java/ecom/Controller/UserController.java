package ecom.Controller;



import ecom.DTO.UserDTO;
import ecom.Helper.Handler.ResponseHandler;
import ecom.Services.UserService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/user")
public class UserController {
 @Autowired
    private  UserService userService;

    @PostMapping("signup")
    public ResponseEntity<Object> signUp(@RequestBody @Valid UserDTO newUser) {
        
        return ResponseHandler.success("success", HttpStatus.CREATED, newUser) ;
    }
    
 
    // @GetMapping("/signup")
    // public List<UserModel> getAll() {
    //     return userService.findAllUser();
    // }
}
