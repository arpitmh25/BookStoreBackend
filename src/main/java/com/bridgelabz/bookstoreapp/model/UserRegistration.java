package com.bridgelabz.bookstoreapp.model;

import com.bridgelabz.bookstoreapp.dto.UserRegistrationDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//Map to a database table
@Entity
//Use to bundle features of getter and setter
@Data
@NoArgsConstructor
//Created UserRegistration class with different fields
@Table(name = "user_details")
public class UserRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userID;

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String password;

    public UserRegistration(UserRegistrationDTO userRegistrationDTO) {
        this.firstName = userRegistrationDTO.getFirstName();
        this.lastName = userRegistrationDTO.getLastName();
        this.email = userRegistrationDTO.getEmail();
        this.address = userRegistrationDTO.getAddress();
        this.password = userRegistrationDTO.getPassword();
    }

    public UserRegistration(Integer userID, UserRegistrationDTO userRegistrationDTO) {
        this.userID = userID;
        this.firstName = userRegistrationDTO.getFirstName();
        this.lastName = userRegistrationDTO.getLastName();
        this.email = userRegistrationDTO.getEmail();
        this.address = userRegistrationDTO.getAddress();
        this.password = userRegistrationDTO.getPassword();

    }
}
