package com.learning.java_web.services.user;

import com.learning.java_web.models.entities.User;
import com.learning.java_web.models.requests.UserRequest;

import java.util.List;

public interface IUserService {
    List<User> getUsers();
    User getUserById(String id);
    void createUser(UserRequest userRequest);
    void updateUser(String id, UserRequest userRequest);
    void deleteUserById(String id);
}
