package ecom.Services.Food;

import ecom.Models.FoodCategoryModel;
import ecom.Models.FoodModel;
import ecom.Models.RestaurantModel;
import java.util.*;

public interface FoodServiceImp {
    
    public FoodModel createFood(FoodModel food, FoodCategoryModel foodCategory, RestaurantModel restaurant);

    public void deleteFood(int foodId) throws Exception;

    public List<FoodModel> getFoodInRestaurant(int restaurantId, boolean isVegitarain, boolean isNonveg, boolean isSeasonal, String foodCategory);

    public List<FoodModel> searchFood(String keyword);

    public FoodModel findById(int foodId) throws Exception;

    public FoodModel updateAvailibityStatus(int foodId) throws Exception;

    public List<FoodModel> filterByVegitarain(List<FoodModel> foods, boolean isVegitarain);
    public List<FoodModel> filterByNonveg(List<FoodModel> foods, boolean isNonveg);
    public List<FoodModel> filterBySeasonal(List<FoodModel> foods, boolean isSeasonal);
    public List<FoodModel> filterByCategory(List<FoodModel> foods, String foodCategory);
    
}
