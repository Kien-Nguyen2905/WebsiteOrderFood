package ecom.Controller.Food;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ecom.Helper.Handler.ResponseHandler;
import ecom.Models.FoodModel;
import ecom.Services.Food.FoodService;

@RestController
@RequestMapping("api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/search")
    public ResponseEntity<Object> searchFood(@RequestHeader("Authorization") String jwt, @RequestParam String name)
            throws Exception {

        List<FoodModel> foods = foodService.searchFood(name);

        return ResponseHandler.success("", HttpStatus.OK, foods);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<Object> getRestaurantFood(@RequestHeader("Authorization") String jwt, @RequestParam boolean vagetarian, 
                                                                                                @RequestParam boolean seasonal, 
                                                                                                @RequestParam boolean nonveq, 
                                                                                                @RequestParam(required = false) String foodCategory,
                                                                                                @RequestParam int restaurantId ) throws Exception {

        List<FoodModel> foods = foodService.getFoodInRestaurant(restaurantId, vagetarian, nonveq, seasonal, foodCategory);

        return ResponseHandler.success("", HttpStatus.OK, foods);
    }

}
