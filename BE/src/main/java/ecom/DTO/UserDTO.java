package ecom.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Date;

import ecom.Models.USER_ROLE;

@Data
public class UserDTO {


     private int id;

    @NotEmpty(message = "Fullname is required")
     private String fullname;

     @NotBlank(message = "Mail is required")
     @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}")
     private String mail;

   @NotBlank(message = "Password is required")
   @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters long")
   @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", 
             message = "Password must contain at least one uppercase letter, one lowercase letter, and one digit, and be at least 8 characters long")
     private String password;

     @NotNull(message = "Phone is required")
     @Pattern(regexp="(^$|[0-9]{8,})")
     private String phone;

     @Past(message = "Birthday must be in the past")
     private Date birthday;

     private USER_ROLE role;

     

    public UserDTO(int id, String fullname, String mail, String password, String phone, Date birthday, USER_ROLE role) {
        
        this.id = id;
        this.fullname = fullname;
        this.mail = mail;
        this.password = password;
        this.phone = phone;
        this.birthday = birthday;
        this.role = role;
        
    }

    public UserDTO() {
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public USER_ROLE getRole() {
        return role;
    }
    public USER_ROLE setRole(USER_ROLE role) {
        return this.role = role;
    }

     
}
