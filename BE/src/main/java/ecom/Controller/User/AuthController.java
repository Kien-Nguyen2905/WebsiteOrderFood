package ecom.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecom.DTO.Auth.AuthDTO;
import ecom.DTO.Auth.LoginDTO;
import ecom.Helper.Handler.ResponseHandler;
import ecom.Models.UserModel;
import ecom.Services.User.AuthService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    // Register User in Customer or Restaurant Owner
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid UserModel newUser) throws Exception {

            AuthDTO authResponse = authService.register(newUser);

            return ResponseHandler.success("Register success", HttpStatus.CREATED, authResponse);
    }

    //Login User in Customer or Restaurant Owner
    @PostMapping("/login")
    public ResponseEntity<Object> logIn(@RequestBody @Valid LoginDTO login) throws Exception {

        AuthDTO authResponse = authService.LoginInByEmail(login);
        
        return ResponseHandler.success("Login success", HttpStatus.OK, authResponse);

    }

}
