package ecom.Controller.Ingredient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecom.Helper.Handler.ResponseHandler;
import ecom.Models.IngredientsCategoryModel;
import ecom.Models.IngredientsItemModel;
import ecom.Services.Ingredient.IngredientService;

@RestController
@RequestMapping("api/admin/ingredient")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;


    // Create Ingredient Category
    @PostMapping("/category")
    public ResponseEntity<Object> createIngredientCategory(@RequestBody IngredientsCategoryModel newCategory) throws Exception {

        IngredientsCategoryModel category = ingredientService.createIngredientsCategory(newCategory.getName(), newCategory.getRestaurant().getId());

        return ResponseHandler.success("Ingredient Category created", HttpStatus.CREATED, category);
    }

    // Create Ingredient Item
    @PostMapping("")
    public ResponseEntity<Object> createIngredientItem(@RequestBody IngredientsItemModel newItem) throws Exception {

        IngredientsItemModel item = ingredientService.createIngredientsItem(newItem.getRestaurant().getId(), newItem.getName(),newItem.getIngredientsCategory().getId());

        return ResponseHandler.success("Ingredient item created", HttpStatus.CREATED, item);
    }

    // Update Ingredient Stock
    @PutMapping("/stoke/{id}")
    public ResponseEntity<Object> updateIngredientStock(@RequestBody IngredientsItemModel newItem, @PathVariable int id) throws Exception {

        IngredientsItemModel item = ingredientService.updateStock(id);

        return ResponseHandler.success("Ingredient item created", HttpStatus.CREATED, item);
    }

    //Get All Ingredient Item in Restaurant
    @GetMapping("/restaurant/{id}")
    public ResponseEntity<Object> getRestaurantIngredient(@RequestBody IngredientsItemModel newItem, @PathVariable int id) throws Exception {

        List<IngredientsItemModel> items = ingredientService.findRestaurantByIngredients(id);
 
        return ResponseHandler.success("Ingredient item created", HttpStatus.CREATED, items);
    }

     //Get All Ingredient Category in Restaurant
    @GetMapping("/category/restaurant/{id}")
    public ResponseEntity<Object> getRestaurantIngredientCategory(@PathVariable int id) throws Exception {

        List<IngredientsCategoryModel> categories = ingredientService.findIngredientsCategoryByRestaurantId(id);
 
        return ResponseHandler.success("Ingredient item created", HttpStatus.CREATED, categories);
    }
}
