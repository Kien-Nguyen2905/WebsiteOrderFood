package ecom.Services.Food;

import java.util.List;

import ecom.Models.FoodCategoryModel;

public interface FoodCategoryServiceImp {

    public FoodCategoryModel createFoodCategory(String name, int userId) throws Exception;

    public List<FoodCategoryModel> findFoodCategoryByRestaurantId(int userId)  throws Exception ;

    public FoodCategoryModel findFoodCategoryById(String name, int Id)   ;
}
