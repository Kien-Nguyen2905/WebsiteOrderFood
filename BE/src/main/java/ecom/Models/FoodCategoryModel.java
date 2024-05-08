package ecom.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "foodcategories")
public class FoodCategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private int id;

      private String name;

    @JsonIgnore
    @ManyToOne
    private RestaurantModel restaurant;
}
