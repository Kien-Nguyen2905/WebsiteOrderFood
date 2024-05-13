package ecom.Controller.Food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecom.Helper.Handler.ResponseHandler;
import ecom.Models.FoodModel;
import ecom.Models.RestaurantModel;
import ecom.Services.Food.FoodService;
import ecom.Services.Restaurant.RestaurantService;

@RestController
@RequestMapping("api/admin/food")
public class AdminFoodController {
        @Autowired
    private FoodService foodService;
 
    @Autowired
    private RestaurantService restaurantService;

    // Create Food 
    @PostMapping
    public ResponseEntity<Object> createFood(@RequestBody FoodModel food) throws Exception {

        
        RestaurantModel restaurant = restaurantService.findById(food.getRestaurant().getId());

        FoodModel foodmModel = foodService.createFood(food, food.getFoodCategory(), restaurant);

        return ResponseHandler.success("Food created", HttpStatus.CREATED, foodmModel);
    }

    //Delete Food by Food Id
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteFoodById(@PathVariable int id) throws Exception {

         foodService.deleteFood(id);

        return ResponseHandler.success("Food deleted", HttpStatus.OK, null);
    }

    //Update Status Avaibility Food by Food Id
    @PostMapping("{id}")
    public ResponseEntity<Object> updateFoodAvaibilityStatus(@PathVariable int id) throws Exception {

         FoodModel foodResponse = foodService.updateAvailibityStatus(id);
        return ResponseHandler.success("Food deleted", HttpStatus.OK, foodResponse);
    }

}
