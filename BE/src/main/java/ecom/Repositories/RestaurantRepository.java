package ecom.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ecom.Models.RestaurantModel;

public interface RestaurantRepository extends JpaRepository<RestaurantModel, Integer> {
    
    @Query("Select r from RestaurantModel r where lower(r.name) like lower(concat('%',:query,'%')) or lower(r.cuisineType) like lower(concat('%',:query,'%'))")
    List<RestaurantModel> findBySearchQuery(String query);

    RestaurantModel findByOwnerId(int ownerId);

}
