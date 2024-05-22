package ecom.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "addresses")
public class AddressModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private int id;
     private String city;
     private String district;
     private String ward;
     private String streetAdress;
     private String number;
}
