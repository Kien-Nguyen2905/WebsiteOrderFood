package ecom.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDTO {
   @NotBlank(message = "Mail is required")
   @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}" ,  message = "Error mail type")
    private String mail;

   @NotBlank(message = "Password is required")
   @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters long")
   @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", 
             message = "Password must contain at least one uppercase letter, one lowercase letter, and one digit, and be at least 8 characters long")
    private String password;
}
