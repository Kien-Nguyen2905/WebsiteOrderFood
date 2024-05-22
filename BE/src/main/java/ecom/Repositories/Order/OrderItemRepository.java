package ecom.Repositories.Order;

import org.springframework.data.jpa.repository.JpaRepository;

import ecom.Models.OrderItemModel;

public interface OrderItemRepository extends JpaRepository<OrderItemModel, Integer> {

}
