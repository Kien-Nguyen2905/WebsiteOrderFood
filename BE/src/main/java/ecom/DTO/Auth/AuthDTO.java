package ecom.DTO.Auth;

import ecom.Models.USER_ROLE;
import lombok.Data;

@Data
public class AuthDTO {
    String jwt;
    private USER_ROLE role;
}
