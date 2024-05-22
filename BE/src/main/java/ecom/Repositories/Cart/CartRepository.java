package ecom.Repositories.Cart;

import org.springframework.data.jpa.repository.JpaRepository;

import ecom.Models.CartModel;

public interface CartRepository extends JpaRepository<CartModel,Integer> {
  
    public CartModel findByCustomerId(int userId);
}
