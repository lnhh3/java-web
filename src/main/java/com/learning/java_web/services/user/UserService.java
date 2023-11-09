package com.learning.java_web.services.user;

import com.learning.java_web.commons.responses.RestApiMessage;
import com.learning.java_web.commons.responses.RestApiStatus;
import com.learning.java_web.commons.validators.Validator;
import com.learning.java_web.models.entities.User;
import com.learning.java_web.models.requests.UserRequest;
import com.learning.java_web.repositories.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{
    @Autowired
    IUserRepo userRepo;

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(String id) {
        User user = userRepo.findById(id).orElse(null);
        Validator.notNull(user, RestApiStatus.NOT_FOUND, RestApiMessage.USER_NOT_FOUND);
        return user;
    }

    @Override
    public void createUser(UserRequest userRequest) {
        System.out.println(userRequest.getName());
        Validator.notNullAndNotEmpty(userRequest.getName(), RestApiStatus.BAD_REQUEST, RestApiMessage.USERNAME_INVALID);
        Validator.notNullAndNotEmpty(userRequest.getEmail(), RestApiStatus.BAD_REQUEST, RestApiMessage.EMAIL_INVALID);
        Validator.notNullAndNotEmpty(userRequest.getPhoneNumber(), RestApiStatus.BAD_REQUEST, RestApiMessage.PHONE_NUMBER_INVALID);

        User user = User.builder()
                .name(userRequest.getName())
                .phoneNumber(userRequest.getPhoneNumber())
                .email(userRequest.getEmail())
                .build();
        userRepo.save(user);
    }

    @Override
    public void updateUser(String id, UserRequest userRequest) {
        User user = userRepo.findById(id).orElse(null);
        Validator.notNull(user, RestApiStatus.NOT_FOUND, RestApiMessage.USER_NOT_FOUND);
        Validator.notNullAndNotEmpty(user.getName(), RestApiStatus.BAD_REQUEST, RestApiMessage.USERNAME_INVALID);
        Validator.notNullAndNotEmpty(user.getEmail(), RestApiStatus.BAD_REQUEST, RestApiMessage.EMAIL_INVALID);
        Validator.notNullAndNotEmpty(user.getPhoneNumber(), RestApiStatus.BAD_REQUEST, RestApiMessage.PHONE_NUMBER_INVALID);
        user = User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .build();
        userRepo.save(user);
    }

    @Override
    public void deleteUserById(String id) {
        User user = userRepo.findById(id).orElse(null);
        Validator.notNull(user, RestApiStatus.NOT_FOUND, RestApiMessage.USER_NOT_FOUND);
        userRepo.deleteById(id);
    }
}
