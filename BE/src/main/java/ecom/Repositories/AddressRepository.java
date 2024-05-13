package ecom.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ecom.Models.AddressModel;

public interface AddressRepository extends JpaRepository<AddressModel, Integer> {

    
} 