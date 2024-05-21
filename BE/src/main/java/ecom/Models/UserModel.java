package ecom.Models;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ecom.DTO.RestaurantDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserModel {    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
     private int id;

     @NotEmpty(message = "Full name is required")
     private String fullName;

     @Email(message = "Email should be valid")
     @NotEmpty(message = "Email is required")
     private String email;

//     @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters long")
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", 
//              message = "Password must contain at least one uppercase letter, one lowercase letter, and one digit, and be at least 8 characters long")
     private String password;

     private String phone;
     private USER_ROLE role;

     @JsonIgnore
     @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
     private List<OrderModel> orders = new ArrayList<>();

    @ElementCollection
     private List<RestaurantDTO> favorites = new ArrayList<>(); 

     @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
     private List<AddressModel> addresses  = new ArrayList<>();


    
}
