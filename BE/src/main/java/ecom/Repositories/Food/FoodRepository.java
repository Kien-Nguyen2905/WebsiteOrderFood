package ecom.Repositories.Food;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ecom.Models.FoodModel;

public interface FoodRepository extends JpaRepository<FoodModel, Integer> {

    List<FoodModel> findByRestaurantId(int restaurantId);
    
    @Query("Select f from FoodModel f where lower(f.name) like lower(concat('%',:keyword,'%')) or lower(f.foodCategory) like lower(concat('%',:keyword,'%'))")
    List<FoodModel> searchFood(@Param("keyword") String keyword);
    
}

