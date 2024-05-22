package ecom.Services.Ingredient;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecom.Helper.Handler.Exceptions.NotFoundException;
import ecom.Models.IngredientsCategoryModel;
import ecom.Models.IngredientsItemModel;
import ecom.Models.RestaurantModel;
import ecom.Repositories.Ingredient.IngredientsCategoryRepository;
import ecom.Repositories.Ingredient.IngredientsItemRepository;
import ecom.Services.Restaurant.RestaurantService;

@Service
public class IngredientService implements IngredientServiceImp {

    @Autowired
    private IngredientsCategoryRepository ingredientsCategoryRepository;

    @Autowired
    private IngredientsItemRepository ingredientsItemRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientsCategoryModel createIngredientsCategory(String name, int restaurantId) throws Exception {

        RestaurantModel findRestaurant = restaurantService.findById(restaurantId);
        IngredientsCategoryModel category = new IngredientsCategoryModel();
        category.setRestaurant(findRestaurant);
        category.setName(name);

        return ingredientsCategoryRepository.save(category);
    }

    @Override
    public IngredientsCategoryModel findIngredientsCategoryById(int id) throws Exception {
       
            Optional<IngredientsCategoryModel> opt = ingredientsCategoryRepository.findById(id);
            if(opt.isEmpty()){
                throw new NotFoundException("Ingredients Category not found");
            }

            return opt.get();
    }

    @Override
    public List<IngredientsCategoryModel> findIngredientsCategoryByRestaurantId(int id) throws Exception {
         
        return ingredientsCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientsItemModel createIngredientsItem(int restaurantId, String ingredientName, int ingredientsCategoryId) throws Exception {

        RestaurantModel restaurant = restaurantService.findById(restaurantId);

        IngredientsCategoryModel category = findIngredientsCategoryById(ingredientsCategoryId);

        IngredientsItemModel item = new IngredientsItemModel();

        item.setName(ingredientName);
        item.setRestaurant(restaurant);
        item.setCategory(category);

            IngredientsItemModel savedIngredientsItem = ingredientsItemRepository.save(item);
            category.getIngredients().add(savedIngredientsItem);

        return savedIngredientsItem;
    }

    @Override
    public List<IngredientsItemModel> findRestaurantByIngredients(int restaurantId) {
       return ingredientsItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItemModel updateStock(int id) throws Exception {
       Optional<IngredientsItemModel> opt = ingredientsItemRepository.findById(id);

       if(opt.isEmpty())
        throw new NotFoundException("Ingredient not found");

        IngredientsItemModel item = opt.get();
        item.setInStock(!item.isInStock());

        return ingredientsItemRepository.save(item);

    }

}
