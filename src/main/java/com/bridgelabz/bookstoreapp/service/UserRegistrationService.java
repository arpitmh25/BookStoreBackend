package com.bridgelabz.bookstoreapp.service;


import com.bridgelabz.bookstoreapp.dto.ChangePasswordDTO;
import com.bridgelabz.bookstoreapp.dto.UserLoginDTO;
import com.bridgelabz.bookstoreapp.dto.UserRegistrationDTO;
import com.bridgelabz.bookstoreapp.exception.UserRegistrationException;
import com.bridgelabz.bookstoreapp.model.UserRegistration;
import com.bridgelabz.bookstoreapp.repository.UserRegistrationRepository;
import com.bridgelabz.bookstoreapp.util.EmailSenderService;
import com.bridgelabz.bookstoreapp.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Slf4j generates logger instance
@Slf4j
@Service
public class UserRegistrationService implements IUserRegistrationService {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;
    @Autowired
    private EmailSenderService mailService;
    @Autowired
    private TokenUtil util;


    //Created service method which serves controller api to post data
    public UserRegistration saveDataToRepo(UserRegistrationDTO userRegistrationDTO) {
        UserRegistration newUserRegistration = new UserRegistration(userRegistrationDTO);
        userRegistrationRepository.save(newUserRegistration);
        return newUserRegistration;
    }

    //Created service method which serves controller api to retrieve all records
    @Override
    public List<UserRegistration> getAllBookStoreData() {
        List<UserRegistration> newUserRegistration = userRegistrationRepository.findAll();
        return newUserRegistration;
    }

    //Created service method which serves controller api to get record by user id
    @Override
    public UserRegistration getUserRecordById(Integer userID) {
        Optional<UserRegistration> newUserRegistration = userRegistrationRepository.findById(userID);
        if (newUserRegistration.isEmpty()) {
            throw new UserRegistrationException("User record does not exist");
        } else {
            return newUserRegistration.get();
        }
    }


    //Created service method which serves controller api to update record by user id
    @Override
    public UserRegistration updateUserRecordById(Integer userID, UserRegistrationDTO userRegistrationDTO) {
        Optional<UserRegistration> userRegistration = userRegistrationRepository.findById(userID);
        if (userRegistration.isEmpty()) {
            throw new UserRegistrationException("User record does not exist");
        } else {
            UserRegistration newUserRegistration = new UserRegistration(userID, userRegistrationDTO);
            userRegistrationRepository.save(newUserRegistration);
            return newUserRegistration;
        }
    }

    //Created service method which serves controller api to  login by user
//    @Override
//    public UserRegistration userLoginDetails(UserLoginDTO userLoginDTO) {
//        Optional<UserRegistration> newUserRegistration = userRegistrationRepository.findByEmail(userLoginDTO.getEmail());
//        if (userLoginDTO.getEmail().equals(newUserRegistration.get().getEmail()) && userLoginDTO.getPassword()
//                .equals(newUserRegistration.get().getPassword())) {
//            log.info("SuccessFully Logged In");
//            return newUserRegistration.get();
//        } else {
//
//            throw new UserRegistrationException("User doesn't exists");
//        }
//    }


    //Created service method which serves controller api to  record by email
    @Override
    public UserRegistration getUserByEmailId(String email) {
        Optional<UserRegistration> newUserRegistration = userRegistrationRepository.findByEmail(email);
        if (newUserRegistration.isEmpty()) {
            throw new UserRegistrationException("User record does not exist");
        } else {
            return newUserRegistration.get();
        }
    }

    //Created service method which serves controller api to  get user password
    @Override
    public UserRegistration changePassword(ChangePasswordDTO passwordDTO) {
        Optional<UserRegistration> userRegistration = userRegistrationRepository.findByEmail(passwordDTO.getEmail());
        String generatedToken = util.createToken(userRegistration.get().getUserID());
        if (userRegistration.isEmpty()) {
            throw new UserRegistrationException("User doesn't exists");
        } else {
            if (passwordDTO.getToken().equals(generatedToken)) {
                userRegistration.get().setPassword(passwordDTO.getNewPassword());
                userRegistrationRepository.save(userRegistration.get());
                log.info("Password changes successfully");
                return userRegistration.get();
            } else {
                throw new UserRegistrationException("Invalid token");
            }
        }
    }

    //Created service method which serves controller api to  get token by email
    @Override
    public String getToken(String email) {
        Optional<UserRegistration> userRegistration = userRegistrationRepository.findByEmail(email);
        String token = util.createToken(userRegistration.get().getUserID());
        mailService.sendEmail(userRegistration.get().getEmail(), "Welcome " + userRegistration.get().getFirstName(),
                "Token for changing password is :\n" + token);
        log.info("Token sent on mail successfully");
        return token;
    }

    //Created service method which serves controller api to  login by user
    @Override
    public UserRegistration userRegistrationDetails(UserLoginDTO userLoginDto) {
        Optional<UserRegistration> userRegistration = userRegistrationRepository.findByEmail(userLoginDto.getEmail());
        System.out.println("Hello there" + userLoginDto.getEmail());
        if (userLoginDto.getPassword().equals(userRegistration.get().getPassword())) {
            return userRegistration.get();
        } else {

            throw new UserRegistrationException("User doesn't exists");
        }
    }

    //Created service method which serves controller api to  change user password
    @Override
    public String forgotPassword(String email, String password) {

        Optional<UserRegistration> isUserPresent = userRegistrationRepository.findByEmail(email);

        if (!isUserPresent.isPresent()) {
            throw new UserRegistrationException("User record does not found");
        } else {
            UserRegistration user = isUserPresent.get();
            user.setPassword(password);
            userRegistrationRepository.save(user);
            return "Password updated successfully";
        }

    }
}




