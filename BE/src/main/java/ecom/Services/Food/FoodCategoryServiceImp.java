package ecom.Services.Food;

import java.util.List;

import ecom.Models.FoodCategoryModel;

public interface FoodCategoryServiceImp {

    public FoodCategoryModel createCategory(String name, int ownerId) throws Exception;

    public List<FoodCategoryModel> findCategoryByRestaurantId(int restaurantId)  throws Exception ;

    public FoodCategoryModel findCategoryById(String name, int foodCategoryId)   ;
}
