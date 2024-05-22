package ecom.Repositories.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import ecom.Models.OrderModel;

public interface OrderRepository extends JpaRepository<OrderModel, Integer> {
    
    public List<OrderModel> findByCustomerId(int customerId);

    public List<OrderModel> findByRestaurantId(int restaurantId);
}
