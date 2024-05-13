package ecom.Controller.Restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ecom.Helper.Handler.ResponseHandler;
import ecom.Models.RestaurantModel;
import ecom.Models.UserModel;
import ecom.Services.Restaurant.RestaurantService;
import ecom.Services.User.UserService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("api/admin/restaurant")
public class AdminRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Object> createRestaurant(@RequestBody RestaurantModel newRestaurant, @RequestHeader("Authorization") String jwt) {

        UserModel user = userService.findByJwtToken(jwt);

        RestaurantModel restaurantResponse = restaurantService.create(newRestaurant, user);

        return ResponseHandler.success("Restaurant created", HttpStatus.CREATED, restaurantResponse);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateRestaurant(@RequestBody RestaurantModel restaurant, @RequestHeader("Authorization") String jwt, @PathVariable int id)  throws Exception {
        
        RestaurantModel restaurantResponse = restaurantService.update(id, restaurant);

        return ResponseHandler.success("Restaurant updated", HttpStatus.OK, restaurantResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRestaurantById( @RequestHeader("Authorization") String jwt, @PathVariable int id) throws Exception {

         restaurantService.detele(id);

        return ResponseHandler.success("Restaurant deleted", HttpStatus.OK,null);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Object> updateRestaurantStatus( @RequestHeader("Authorization") String jwt,@PathVariable int id)  throws Exception {
       
        RestaurantModel restaurantResponse = restaurantService.updateRestaurantStatus(id);

        return ResponseHandler.success("Restaurant status updated", HttpStatus.OK, restaurantResponse);
    }
    
    @GetMapping("/user")
    public ResponseEntity<Object> getRestaurantByOwnerId( @RequestHeader("Authorization") String jwt)  throws Exception {

        UserModel user = userService.findByJwtToken(jwt);

        RestaurantModel restaurantResponse = restaurantService.findByUserId(user.getId());
        
        return ResponseHandler.success("", HttpStatus.OK, restaurantResponse);
    }
}
