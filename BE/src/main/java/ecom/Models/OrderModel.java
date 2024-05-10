package ecom.Models;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private int id;

     @ManyToOne
     private UserModel customer;

     @JsonIgnore
     @ManyToOne
    private RestaurantModel restaurant;

     private BigDecimal totalAmount;
    private String orderStatus;
    private Date createdAt;

    @ManyToOne
    private AddressModel deliveryAddress;

    @OneToMany
    private List<OrderItemModel> items;
     
      // private PaymentModel payment;

      private int totalItem;
      private BigDecimal totalPrice;
}
