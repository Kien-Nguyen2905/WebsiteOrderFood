package ecom.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ecom.DTO.AuthDTO;
import ecom.DTO.LoginDTO;
import ecom.DTO.UserDTO;
import ecom.Helper.ResponseHandler;
import ecom.Models.UserModel;

import ecom.Services.AuthService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserDTO User) throws Exception {

        AuthDTO authResponse = authService.register(User);

        return ResponseHandler.success("success", "created user", HttpStatus.OK, authResponse);

    }

    @PostMapping("/login")
    public ResponseEntity<Object> logIn(@RequestBody @Valid LoginDTO login) throws Exception {

        try {
            AuthDTO authResponse = authService.LoginInByMail(login);
            return ResponseHandler.success("success", "created user", HttpStatus.OK, authResponse);
        } catch (Exception e) {
            throw new Exception("login fail");
        }

    }

}
