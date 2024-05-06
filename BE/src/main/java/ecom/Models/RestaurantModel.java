package ecom.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
     private int id;

     @OneToOne
     private UserModel owner;

     private String name;

     private String description;

     private String cuisineType;

     @OneToOne
     private AddressModel address;

     @Embedded
     private ContactInfomationModel ContactInfomation;

     private String openingHours;
     


}
