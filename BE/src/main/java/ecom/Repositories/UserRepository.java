package ecom.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import ecom.Models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

}