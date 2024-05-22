package ecom.Repositories.Cart;

import org.springframework.data.jpa.repository.JpaRepository;

import ecom.Models.CartItemModel;

public interface CartItemRepository extends JpaRepository<CartItemModel,Integer> {

}
