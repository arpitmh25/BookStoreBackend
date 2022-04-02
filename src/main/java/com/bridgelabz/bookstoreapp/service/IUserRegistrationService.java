package com.bridgelabz.bookstoreapp.service;

import com.bridgelabz.bookstoreapp.dto.ChangePasswordDTO;
import com.bridgelabz.bookstoreapp.dto.UserLoginDTO;
import com.bridgelabz.bookstoreapp.dto.UserRegistrationDTO;
import com.bridgelabz.bookstoreapp.model.UserRegistration;

import java.util.List;

//Created interface for all service methods to achieve abstraction
public interface IUserRegistrationService {
    public UserRegistration saveDataToRepo(UserRegistrationDTO userRegistrationDTO);

    public List<UserRegistration> getAllBookStoreData();

    public UserRegistration getUserRecordById(Integer userID);

    public UserRegistration updateUserRecordById(Integer userID, UserRegistrationDTO userRegistrationDTO);


    public UserRegistration getUserByEmailId(String email);


//    public UserRegistration userLoginDetails(UserLoginDTO userLoginDTO);

    public UserRegistration changePassword(ChangePasswordDTO passwordDTO);

    public String getToken(String email);

    UserRegistration userRegistrationDetails(UserLoginDTO userLoginDto);

    public String forgotPassword(String email, String password);
}
