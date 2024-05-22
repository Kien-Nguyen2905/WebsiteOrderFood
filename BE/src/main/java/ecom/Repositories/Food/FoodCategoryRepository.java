package ecom.Repositories.Food;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ecom.Models.FoodCategoryModel;

public interface FoodCategoryRepository extends JpaRepository<FoodCategoryModel,Integer>{
    
    public List<FoodCategoryModel> findByRestaurantId(int id);
}
