package com.learning.java_web.controllers;

import com.learning.java_web.models.requests.UserRequest;
import com.learning.java_web.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController extends AbstractBaseController{
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity getUsers() {
        return responseUtil.successResponse(userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable("id") String id) {
        return responseUtil.successResponse(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
        return responseUtil.successResponse("ok");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable("id") String id, @RequestBody UserRequest userRequest) {
        userService.updateUser(id, userRequest);
        return responseUtil.successResponse("ok");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") String id) {
        userService.deleteUserById(id);
        return responseUtil.successResponse("ok");
    }
}
