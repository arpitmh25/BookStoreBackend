package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.ChangePasswordDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.dto.UserLoginDTO;
import com.bridgelabz.bookstoreapp.dto.UserRegistrationDTO;
import com.bridgelabz.bookstoreapp.model.UserRegistration;
import com.bridgelabz.bookstoreapp.service.IUserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
//Created controller so that we can perform rest api calls
@RestController
//Maps http request to method of mvc and rest controller
@RequestMapping("/book")
public class UserRegistrationController {

    //Autowired IUserRegistrationService interface so we can inject its dependency here
    @Autowired
    IUserRegistrationService service;

    //Ability to store  books  record to repository
    @PostMapping("/createuserdetail")
    public ResponseEntity<String> saveDataToRepo(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        UserRegistration newUserRegistration = service.saveDataToRepo(userRegistrationDTO);
        ResponseDTO responseDTO = new ResponseDTO("User Record created successfully", newUserRegistration);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    //Ability to get all record from repository
    @GetMapping("/getall")
    public ResponseEntity<String> getBookStoreData() {
        List<UserRegistration> newUserRegistration = service.getAllBookStoreData();
        ResponseDTO responseDTO = new ResponseDTO("User Record for particular id retrieved successfully", newUserRegistration);
        return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);

    }

    //Ability to get user record by id
    @GetMapping("/getuser/{userID}")
    public ResponseEntity<ResponseDTO> getRecordFromRepoByID(@PathVariable Integer userID) {
        UserRegistration newUserRegistration = service.getUserRecordById(userID);
        ResponseDTO responseDTO = new ResponseDTO(" User Record for particular id retrieved successfully", newUserRegistration);
        return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
    }

    //Ability to update user record to repository
    @PutMapping("/updateuser/{userID}")
    public ResponseEntity<ResponseDTO> updateRecordById(@PathVariable Integer userID, @Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        UserRegistration newUserRegistration = service.updateUserRecordById(userID, userRegistrationDTO);
        ResponseDTO responseDTO = new ResponseDTO(" Book Record updated successfully", newUserRegistration);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

//    //Ability to update user record to repository
//    @GetMapping("/userlogin")
//    public ResponseEntity userLoginDetails(@RequestBody UserLoginDTO userLoginDTO) {
//        UserRegistration newUserRegistration = service.userLoginDetails(userLoginDTO);
//        ResponseDTO responseDTO = new ResponseDTO("User logged in successfully !", newUserRegistration);
//        return new ResponseEntity(responseDTO, HttpStatus.OK);
//    }

    //Ability to get user record to repository
    @GetMapping("/userlogin")
    public ResponseEntity userLoginDetails(@RequestParam String email, @RequestParam String password) {
        UserLoginDTO userLoginDto = new UserLoginDTO();
        userLoginDto.setEmail(email);
        userLoginDto.setPassword(password);
        UserRegistration userRegistration = service.userRegistrationDetails(userLoginDto);
        return new ResponseEntity(userRegistration, HttpStatus.OK);
    }

    //Ability to  user record to repository by email
    @GetMapping("/getbyemail/{email}")
    public ResponseEntity<ResponseDTO> getUserByEmailId(@PathVariable String email) {
        UserRegistration userRegistration = service.getUserByEmailId(email);
        ResponseDTO responseDTO = new ResponseDTO("Record for email successfully", userRegistration);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    //Ability to get user record to repository forget password by email
    @PutMapping("/changepassword")
    public ResponseEntity<ResponseDTO> forgotPassword(@Valid @RequestBody ChangePasswordDTO passwordDTO) {
        UserRegistration newUserRegistration = service.changePassword(passwordDTO);
        ResponseDTO responseDTO = new ResponseDTO("Password Reset successfully !", newUserRegistration);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    //Forget password by email
    @PostMapping("/forgotPassword")
    public ResponseEntity<String> forgotPassword(@RequestParam String email, @RequestParam String password) {
        String responseDTO = service.forgotPassword(email, password);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }


    //Ability to get user record to repository to get token by email
    @GetMapping("/getToken/{email}")
    public ResponseEntity<ResponseDTO> getToken(@PathVariable String email) {
        String generatedToken = service.getToken(email);
        ResponseDTO responseDTO = new ResponseDTO("Token for mail id sent on mail successfully !", generatedToken);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

}
