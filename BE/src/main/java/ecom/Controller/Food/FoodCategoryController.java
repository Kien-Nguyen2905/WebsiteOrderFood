package ecom.Controller.Food;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecom.Helper.Handler.ResponseHandler;
import ecom.Models.FoodCategoryModel;
import ecom.Models.UserModel;
import ecom.Services.Food.FoodCategoryService;
import ecom.Services.User.UserService;

@RestController
@RequestMapping("api/admin/food/category")
public class FoodCategoryController {
    
    @Autowired
    private FoodCategoryService foodCategoryService;

    @Autowired
    private UserService userService;

    //Create Food Category 
    @PostMapping
    public ResponseEntity<Object> createFoodCategory(@RequestBody FoodCategoryModel newCategory, @RequestHeader("Authorization") String jwt) throws Exception {

        UserModel findUser = userService.findByJwtToken(jwt);

        String categoryName= newCategory.getName();
        int ownerId= findUser.getId();

       FoodCategoryModel categoryResponse = foodCategoryService.createCategory(categoryName,ownerId);

        return ResponseHandler.success("Food Category created", HttpStatus.CREATED, categoryResponse);
    }

    //Get all food category in restaurant
    @GetMapping
    public ResponseEntity<Object> getRestaurantCategory(@RequestHeader("Authorization") String jwt) throws Exception {

        UserModel findUser = userService.findByJwtToken(jwt);

        int ownerId= findUser.getId();

       List<FoodCategoryModel> foodCategoriesResponse = foodCategoryService.findCategoryByRestaurantId(ownerId);

        return ResponseHandler.success("", HttpStatus.OK, foodCategoriesResponse);
    }

}
