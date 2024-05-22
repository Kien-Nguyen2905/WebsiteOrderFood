package ecom.Controller.Restaurant;

import org.apache.coyote.BadRequestException;
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

    //Create restaurant
    @PostMapping
    public ResponseEntity<Object> createRestaurant(@RequestHeader("Authorization") String jwt, @RequestBody RestaurantModel newRestaurant ) throws Exception {

        UserModel findUser = userService.findByJwtToken(jwt);

        RestaurantModel restaurantResponse = restaurantService.create(newRestaurant, findUser);

        return ResponseHandler.success("Restaurant created", HttpStatus.CREATED, restaurantResponse);
    }

    // Update restaurant by restaurant id
    @PutMapping("{id}")
    public ResponseEntity<Object> updateRestaurant(@RequestBody RestaurantModel restaurant, @PathVariable int id )  throws Exception {
        
        RestaurantModel restaurantResponse = restaurantService.update(id, restaurant);

        return ResponseHandler.success("Restaurant updated", HttpStatus.OK, restaurantResponse);
    }

      // Delete restaurant by restaurant id
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRestaurantById(@PathVariable int id) throws Exception {

         restaurantService.detele(id);

         RestaurantModel findUser = restaurantService.findByOwnerId(id);

         if (findUser== null) {
            throw new BadRequestException("Restaurant delete fail");
         }

        return ResponseHandler.success("Restaurant deleted", HttpStatus.OK,null);
    }

    //update status open restaurant by restaurant id
    @PutMapping("/status/{id}")
    public ResponseEntity<Object> updateRestaurantStatus(@PathVariable int id)  throws Exception {
       
        RestaurantModel restaurantResponse = restaurantService.updateRestaurantStatus(id);

        return ResponseHandler.success("Restaurant status updated", HttpStatus.OK, restaurantResponse);
    }
    
    //Get restaurant by owner id
    @GetMapping("/user")
    public ResponseEntity<Object> getRestaurantByOwnerId( @RequestHeader("Authorization") String jwt)  throws Exception {

        UserModel findOwner = userService.findByJwtToken(jwt);
        int ownerId = findOwner.getId();

        RestaurantModel restaurantResponse = restaurantService.findByOwnerId(ownerId);
        
        return ResponseHandler.success("", HttpStatus.OK, restaurantResponse);
    }
}
