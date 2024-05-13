package ecom.Models;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ecom.DTO.ContactInfomationDTO;

import java.time.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "restaurents")
public class RestaurantModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // @Column(name = "id")
     private int id;

     @OneToOne
     private UserModel owner;

     private String name;

     private String description;

     private String cuisineType;

     @OneToOne
     private AddressModel address;

      @Embedded
      private ContactInfomationDTO contact;

     private String openingHours;
     
     @OneToMany(mappedBy = "restaurant",cascade =  CascadeType.ALL, orphanRemoval = true)
  private List<OrderModel> orders = new ArrayList<>();

  @ElementCollection
  @Column(length = 1000)
  private List<String> images;

  private LocalDateTime registrationDate ;

  private boolean open;

  @JsonIgnore
  @OneToMany(mappedBy = "restaurant",cascade =  CascadeType.ALL)
  private List<FoodModel> foods = new ArrayList<>();
}
