package ecom.Repositories.Ingredient;

import org.springframework.data.jpa.repository.JpaRepository;

import ecom.Models.IngredientsCategoryModel;
import java.util.List;



public interface IngredientsCategoryRepository extends JpaRepository<IngredientsCategoryModel, Integer> {
    List<IngredientsCategoryModel> findByRestaurantId(int id);
}
