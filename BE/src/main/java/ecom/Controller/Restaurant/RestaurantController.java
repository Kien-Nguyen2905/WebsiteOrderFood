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

    //Search restaurant by keyword
    @GetMapping("/search")
    public ResponseEntity<Object> searchRestaurants(@RequestParam(name = "keyword") String keyword) {

        List<RestaurantModel> restaurantsResponse = restaurantService.search(keyword);

        return ResponseHandler.success("", HttpStatus.OK, restaurantsResponse);
    }

    //Get all restaurant
    @GetMapping
    public ResponseEntity<Object> getAllRestaurant() throws Exception {

        List<RestaurantModel> restaurantsResponse = restaurantService.getAll();

        return ResponseHandler.success("", HttpStatus.OK, restaurantsResponse);
    }

     //Get restaurant by restaurant id
    @GetMapping("{id}")
    public ResponseEntity<Object> getRestaurantById(@PathVariable int id) throws Exception {

        RestaurantModel restaurantResponse = restaurantService.findById(id);
      
        return ResponseHandler.success("", HttpStatus.OK, restaurantResponse);
    }

    // add restaurant dto to list favorites user
    @PutMapping("/add-favorites/{id}")
    public ResponseEntity<Object> addFavorites( @RequestHeader("Authorization") String jwt,@PathVariable int id) throws Exception {
       
        UserModel findUser = userService.findByJwtToken(jwt);

        RestaurantDTO restaurantResponse = restaurantService.addToFavorites(id, findUser);

        return ResponseHandler.success("Update favorites succes", HttpStatus.CREATED, restaurantResponse);
    }
}
