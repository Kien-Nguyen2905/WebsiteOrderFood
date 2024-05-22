package ecom.Services.Food;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecom.Helper.Handler.Exceptions.NotFoundException;
import ecom.Models.FoodCategoryModel;
import ecom.Models.RestaurantModel;
import ecom.Repositories.Food.FoodCategoryRepository;
import ecom.Services.Restaurant.RestaurantService;

@Service
public class FoodCategoryService implements FoodCategoryServiceImp {

    @Autowired
    public FoodCategoryRepository foodCategoryRepository;

    @Autowired
    public RestaurantService restaurantService;

    
    @Override
    public FoodCategoryModel createCategory(String categoryName, int ownerId) throws Exception {

        
        RestaurantModel restaurant = restaurantService.findByOwnerId(ownerId);

        FoodCategoryModel category = new FoodCategoryModel();

        category.setName(categoryName);

        category.setRestaurant(restaurant);

        return foodCategoryRepository.save(category);
    }

    @Override
    public List<FoodCategoryModel> findCategoryByRestaurantId(int restaurantId) throws Exception {

        RestaurantModel restaurant = restaurantService.findByOwnerId(restaurantId);
        
       return foodCategoryRepository.findByRestaurantId(restaurant.getId());
    }

    @Override
    public FoodCategoryModel findCategoryById(String name, int id)  {
        Optional<FoodCategoryModel> optionalFoodCategory = foodCategoryRepository.findById(id);
        
        if (optionalFoodCategory.isEmpty()) {
            throw new NotFoundException("Food Category not found");
        }
        return optionalFoodCategory.get();
    }

}
