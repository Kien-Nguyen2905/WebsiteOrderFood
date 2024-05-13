package ecom.Services.Ingredient;

import java.util.List;

import ecom.Models.IngredientsCategoryModel;
import ecom.Models.IngredientsItemModel;

public interface IngredientServiceImp {

 public IngredientsCategoryModel createIngredientsCategory(String name, int restaurantId) throws Exception;

 public IngredientsCategoryModel findIngredientsCategoryById( int id) throws Exception;

 public List<IngredientsCategoryModel> findIngredientsCategoryByRestaurantId(int id) throws Exception;

 public IngredientsItemModel createIngredientsItem(int restaurantId, String ingredientName, int ingredientsCategoryId ) throws Exception;

 public List<IngredientsItemModel> findRestaurantByIngredients(int restaurantId) ;

 public IngredientsItemModel updateStock(int id) throws Exception;
}
