package ecom.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ecom.Models.CartModel;

public interface CartRepository extends JpaRepository<CartModel, Integer> {
    
}
