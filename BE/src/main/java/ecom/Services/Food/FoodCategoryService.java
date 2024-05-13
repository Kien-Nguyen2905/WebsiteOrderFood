package ecom.Services.Food;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecom.Helper.Handler.Exceptions.NotFoundException;
import ecom.Models.FoodCategoryModel;
import ecom.Models.RestaurantModel;
import ecom.Repositories.FoodCategoryRepository;
import ecom.Services.Restaurant.RestaurantService;

@Service
public class FoodCategoryService implements FoodCategoryServiceImp {

    @Autowired
    public FoodCategoryRepository foodCategoryRepository;

    @Autowired
    public RestaurantService restaurantService;

    @Override
    public FoodCategoryModel createFoodCategory(String name, int userId) throws Exception {

        
        RestaurantModel restaurant = restaurantService.findByUserId(userId);

        FoodCategoryModel categorys = new FoodCategoryModel();

        categorys.setName(name);

        categorys.setRestaurant(restaurant);

        return foodCategoryRepository.save(categorys);
    }

    @Override
    public List<FoodCategoryModel> findFoodCategoryByRestaurantId(int userId) throws Exception {

        RestaurantModel restaurant = restaurantService.findByUserId(userId);
        
       return foodCategoryRepository.findByRestaurantId(restaurant.getId());
    }

    @Override
    public FoodCategoryModel findFoodCategoryById(String name, int id)  {
        Optional<FoodCategoryModel> optionalFoodCategory = foodCategoryRepository.findById(id);
        
        if (optionalFoodCategory.isEmpty()) {
            throw new NotFoundException("Food Category not found");
        }
        return optionalFoodCategory.get();
    }

}
