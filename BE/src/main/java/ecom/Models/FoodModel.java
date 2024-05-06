package ecom.Models;

import java.math.BigDecimal;
import java.util.*;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "foods")
public class FoodModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
     private int id;

     private String name;

     private String description;

     private BigDecimal price;

     @ManyToOne
     private FoodCategoryModel foodCategory;

     @Column(length = 1000)
     @ElementCollection
     private List<String> images;

     private boolean available;

     @ManyToOne
     private RestaurantModel restaurant;

     private boolean isVegetarian;
     private boolean isSeasonal;

     @ManyToMany
     private List<IngredientsModel> ingredients = new ArrayList<>();

     private Date createDate; 
}
