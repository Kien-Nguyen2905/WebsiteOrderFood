package ecom.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ecom.Models.UserModel;


public interface UserRepository extends JpaRepository<UserModel, Integer> {

    public UserModel findByMail(String mail);
    public UserModel findByPhone(String phone);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UserModel u WHERE u.mail = :email OR u.phone = :phone")
    boolean findByEmailOrPhone(String email, String phone);
}