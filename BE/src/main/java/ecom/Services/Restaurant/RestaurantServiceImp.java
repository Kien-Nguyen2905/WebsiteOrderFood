package ecom.Services.Restaurant;

import java.util.List;

import ecom.DTO.RestaurantDTO;
import ecom.Models.RestaurantModel;
import ecom.Models.UserModel;


public interface RestaurantServiceImp {
    public RestaurantModel create(RestaurantModel  newRestaurant, UserModel user) throws Exception;

    public RestaurantModel update(int restaurantId , RestaurantModel restaurant) throws Exception;

    public void detele(int restaurantId) throws Exception;

    public List<RestaurantModel> getAll() throws Exception;

    public List<RestaurantModel> search(String keyword);

    public RestaurantModel findById(int restaurantId) throws Exception; 

    public RestaurantModel findByOwnerId(int ownerId) throws Exception; 

    public RestaurantDTO addToFavorites(int restaurantId, UserModel user) throws Exception; 

    public RestaurantModel updateRestaurantStatus(int restaurantId) throws Exception; 

}
