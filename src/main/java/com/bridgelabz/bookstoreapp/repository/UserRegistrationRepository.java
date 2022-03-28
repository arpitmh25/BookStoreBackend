package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.model.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

//Created UserRegistrationRepository extending JpaRepository so that we perform CURD and can implement custom query methods
public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Integer> {

    @Query(value = "SELECT * FROM user_details where email =:email", nativeQuery = true)
    Optional<UserRegistration> findByEmail(String email);
}
