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
import java.util.*;
import java.math.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cartitems")
public class CartItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private int id;

     @JsonIgnore
     @ManyToOne
     private CartModel cart;

    @ManyToOne
    private FoodModel food;
    
    private int quantity;
    private List<String> ingredients;
    private BigDecimal totalPrice;
}
