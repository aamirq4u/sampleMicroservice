package com.example.user.service.UserService.services;

import com.example.user.service.UserService.entities.User;

import java.util.List;

public interface UserService {
    // user operation

    // create
    User saveUser(User user);

    // get all user
    List<User> getAllUser();

    // get single user of given user id
    User getUser(String userId);

    // todo delete
    // todo update
}
