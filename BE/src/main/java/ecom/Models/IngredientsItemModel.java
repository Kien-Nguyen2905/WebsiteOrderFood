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
@Table(name = "ingredients")
public class IngredientsItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private int id;
    private String name;

    @ManyToOne
    private IngredientsCategoryModel ingredientsCategory;

    @JsonIgnore
    @ManyToOne
    private RestaurantModel restaurant;

    private boolean inStock;


}
