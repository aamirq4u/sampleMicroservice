package com.example.user.service.UserService.services.impl;

import com.example.user.service.UserService.entities.Rating;
import com.example.user.service.UserService.entities.User;
import com.example.user.service.UserService.exception.ResourceNotFoundException;
import com.example.user.service.UserService.repositories.UserRepository;
import com.example.user.service.UserService.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        ///  todo needs to implement getRating here also as below in getUser()
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User with given id not found on server !!! : " + userId));

        // fetch rating of the above user from RATING-SERVICE
        // http://localhost:8083/ratings/users/8afa303e-acb7-4e93-af10-e51551de67fd

        //ArrayList<Rating> ratingOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/8afa303e-acb7-4e93-af10-e51551de67fd", ArrayList.class);
        ArrayList<Rating> ratingOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(), ArrayList.class);
       // log.info("User Controller -> getUser(): getForObject() "+ratingOfUser.toString());
        user.setRatingList(ratingOfUser);
        return user;



    }
}
