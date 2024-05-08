package ecom.Models;

import java.math.BigDecimal;
import java.util.*;

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
@Table(name = "orderitems")
public class OrderItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private int id;

     @ManyToOne
     private FoodModel food;

     private int quantity;

    private BigDecimal totalPrice;

    
     private List<String> ingredients;
}
