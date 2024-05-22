package ecom.Services.Food;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecom.Models.FoodCategoryModel;
import ecom.Models.FoodModel;
import ecom.Models.RestaurantModel;
import ecom.Repositories.Food.FoodRepository;

@Service
public class FoodService implements FoodServiceImp {

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public FoodModel createFood(FoodModel food, FoodCategoryModel foodCategory, RestaurantModel restaurant) {
        food.setFoodCategory(foodCategory);
        food.setRestaurant(restaurant);

        FoodModel savedFood = foodRepository.save(food);
        restaurant.getFoods().add(savedFood);
        return savedFood;
    }

    @Override
    public void deleteFood(int foodId) throws Exception {
        FoodModel food = findById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food);
    }

    @Override
    public List<FoodModel> getFoodInRestaurant(int restaurantId, boolean isVegitarain, boolean isNonveg,
            boolean isSeasonal, String foodCategory) {

        List<FoodModel> foods = foodRepository.findByRestaurantId(restaurantId);

        if (isVegitarain)
            foods = filterByVegitarain(foods, isVegitarain);
        if (isSeasonal)
            foods = filterBySeasonal(foods, isSeasonal);
        if (isNonveg)
            foods = filterBySeasonal(foods, isNonveg);

        if (foodCategory != null && !foodCategory.equals(""))
            foods = filterByCategory(foods, foodCategory);

        return foods;
    }

    @Override
    public List<FoodModel> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public FoodModel findById(int foodId) throws Exception {
        Optional<FoodModel> optionalFood= foodRepository.findById(foodId);

        if(optionalFood.isEmpty()){
            throw new Exception("Food not exist");
        }
        return optionalFood.get();
    }

    @Override
    public FoodModel updateAvailibityStatus(int foodId) throws Exception {
      FoodModel food = findById(foodId);
      food.setAvailable(!food.isAvailable());
      return foodRepository.save(food);
    }

    @Override
    public List<FoodModel> filterByVegitarain(List<FoodModel> foods, boolean isVegitarain) {
        return foods.stream().filter(food -> food.isVegetarian() == isVegitarain).collect(Collectors.toList());
    }

    @Override
    public List<FoodModel> filterByNonveg(List<FoodModel> foods, boolean isNonveg) {
        return foods.stream().filter(food -> food.isVegetarian() == false).collect(Collectors.toList());
    }
    

    @Override
    public List<FoodModel> filterBySeasonal(List<FoodModel> foods, boolean isSeasonal) {
        return foods.stream().filter(food -> food.isSeasonal() == isSeasonal).collect(Collectors.toList());
    }
    

    @Override
    public List<FoodModel> filterByCategory(List<FoodModel> foods, String foodCategory) {
        return foods.stream().filter(food ->{
            if (food.getFoodCategory() != null) {
                return food.getFoodCategory().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());
    }

}
