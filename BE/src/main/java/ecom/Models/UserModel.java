package ecom.Models;

import java.util.*;

import java.sql.Date;

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
     private String fullname;
     private String mail;
     private String password;
     private String phone;
     private Date birthday;
     private USER_ROLE role ;

     @JsonIgnore
     @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
     private List<OrderModel> orders = new ArrayList<>();

    @ElementCollection
     private List<RestaurantDTO> favorites = new ArrayList<>(); 

     @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
     private List<AddressModel> addresses = new ArrayList<>();


    
}
