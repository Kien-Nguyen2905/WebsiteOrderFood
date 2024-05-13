package ecom.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ecom.Models.IngredientsItemModel;

public interface IngredientsItemRepository extends JpaRepository<IngredientsItemModel, Integer>{

    List<IngredientsItemModel> findByRestaurantId(int id);
}
