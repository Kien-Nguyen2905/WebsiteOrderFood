package ecom.Controller.Restaurant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecom.DTO.RestaurantDTO;
import ecom.Helper.Handler.ResponseHandler;
import ecom.Models.RestaurantModel;
import ecom.Models.UserModel;
import ecom.Services.Restaurant.RestaurantService;
import ecom.Services.User.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/restaurant")
public class RestaurantController {
   @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<Object> searchRestaurants(@RequestHeader("Authorization") String jwt, @RequestParam(name = "keyword") String keyword) {

        List<RestaurantModel> restaurants = restaurantService.search(keyword);
        return ResponseHandler.success("", HttpStatus.OK, restaurants);
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllRestaurant() {


        List<RestaurantModel> restaurants = restaurantService.getAll();
        return ResponseHandler.success("", HttpStatus.OK, restaurants);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getRestaurantById(@RequestHeader("Authorization") String jwt, @PathVariable int id) throws Exception {
        RestaurantModel restaurantModel = restaurantService.findById(id);
      
        return ResponseHandler.success("", HttpStatus.OK, restaurantModel);
    }

    @PutMapping("/add-favorites/{id}")
    public ResponseEntity<Object> addFavorites( @RequestHeader("Authorization") String jwt,@PathVariable int id) throws Exception {
       
        if (jwt != null) {
            jwt = jwt.substring(7);
        }   
        
        UserModel user = userService.findByJwtToken(jwt);
        RestaurantDTO restaurant = restaurantService.addToFavorites(id, user);

      
        return ResponseHandler.success("Add favorites succes", HttpStatus.CREATED, restaurant);
    }
}