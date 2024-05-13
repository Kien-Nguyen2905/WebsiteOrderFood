package ecom.Services.Restaurant;

import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ecom.DTO.RestaurantDTO;
import ecom.Helper.Handler.Exceptions.NotFoundException;
import ecom.Models.AddressModel;
import ecom.Models.RestaurantModel;
import ecom.Models.UserModel;
import ecom.Repositories.AddressRepository;
import ecom.Repositories.RestaurantRepository;
import ecom.Repositories.UserRepository;
import ecom.Services.User.UserService;
import java.time.*;

@Service
public class RestaurantService implements RestaurantServiceImp {
    @Autowired
    public RestaurantRepository restaurantRepository;

    @Autowired
    public AddressRepository addressRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RestaurantModel create(RestaurantModel newRestaurant, UserModel user) {

        // save address
        AddressModel savedAddress = addressRepository.save(newRestaurant.getAddress());

        newRestaurant.setRegistrationDate(LocalDateTime.now());
        newRestaurant.setAddress(savedAddress);
        newRestaurant.setOwner(user);

        return restaurantRepository.save(newRestaurant);
    }

    @Override
    public RestaurantModel update(int restaurantId, RestaurantModel restaurant) throws Exception {
        RestaurantModel findRestaurantModel = findById(restaurantId);

        if (findRestaurantModel.getCuisineType() != null)
            findRestaurantModel.setCuisineType(restaurant.getCuisineType());

        if (findRestaurantModel.getDescription() != null)
            findRestaurantModel.setDescription(restaurant.getDescription());

        if (findRestaurantModel.getName() != null)
            findRestaurantModel.setName(restaurant.getName());

        return restaurantRepository.save(findRestaurantModel);
    }

    @Override
    public void detele(int restaurantId) throws Exception {
        RestaurantModel restaurant = findById(restaurantId);

          restaurantRepository.delete(restaurant);
    }

    @Override
    public List<RestaurantModel> getAll() {
        List<RestaurantModel> list = restaurantRepository.findAll();
        return list;
    }

    @Override
    public List<RestaurantModel> search(String keyword) {

       return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public RestaurantModel findById(int restaurantId) throws Exception {
    Optional<RestaurantModel> opt = restaurantRepository.findById(restaurantId);
    if(opt.isEmpty())
        throw new NotFoundException("Restaurant not found'");
        return opt.get();
    }

    @Override
    public RestaurantModel findByUserId(int userId) throws Exception {

        RestaurantModel restaurantModel = restaurantRepository.findByOwnerId(userId);

        if(restaurantModel == null)
            throw new NotFoundException("Restaurant not found'");

        return restaurantModel;
    }

    @Override
    public RestaurantDTO addToFavorites(int restaurantId, UserModel user) throws Exception {

        RestaurantModel restaurantModel = findById(restaurantId);

        RestaurantDTO restaurantDTO = modelMapper.map(restaurantModel, RestaurantDTO.class);
        restaurantDTO.setTitle(restaurantModel.getName());

    boolean isFavorited = false;
    List<RestaurantDTO> favorites = user.getFavorites();
    for(RestaurantDTO favorite : favorites){
        if(favorite.getId()==restaurantId){
            isFavorited = true;
            break;
        }
    }


    if (isFavorited) {
        favorites.removeIf(favorite -> favorite.getId() == restaurantId);
    } else {
        favorites.add(restaurantDTO);
    }
    userRepository.save(user);

        return restaurantDTO;
    }

    @Override
    public RestaurantModel updateRestaurantStatus(int restaurantId) throws Exception {
        RestaurantModel restaurantModel = findById(restaurantId);

        restaurantModel.setOpen(!restaurantModel.isOpen());
         


        return restaurantRepository.save(restaurantModel);
    }

}
