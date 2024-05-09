package ecom.Controller;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ecom.DTO.AuthDTO;
import ecom.DTO.LoginDTO;
import ecom.DTO.UserDTO;
import ecom.Helper.Handler.ResponseHandler;
import ecom.Services.AuthService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid UserDTO User) throws Exception {

            AuthDTO authResponse = authService.register(User);
            return ResponseHandler.success("Register success", HttpStatus.OK, authResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> logIn(@RequestBody @Valid LoginDTO login) throws Exception {

        AuthDTO authResponse = authService.LoginInByMail(login);
        return ResponseHandler.success("Login success", HttpStatus.OK, authResponse);

    }

}
