package com.example.user.service.UserService.services.impl;

import com.example.user.service.UserService.entities.Hotel;
import com.example.user.service.UserService.entities.Rating;
import com.example.user.service.UserService.entities.User;
import com.example.user.service.UserService.exception.ResourceNotFoundException;
import com.example.user.service.UserService.external.service.HotelService;
import com.example.user.service.UserService.external.service.RatingService;
import com.example.user.service.UserService.repositories.UserRepository;
import com.example.user.service.UserService.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;
    @Autowired
    private RatingService ratingService;

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
        //Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        // log.info("User Controller -> getUser(): getForObject() "+ratingOfUser.toString());

        Rating[] ratingsOfUser = ratingService.getRating(user.getUserId());

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {
            // api call to hotel service to get hotel
            //http://localhost:8082/hotels/4860804a-6c78-42ad-bd97-7bd3f79671ee
            /*ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+ rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();*/

            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            // log.info("User Controller -> getUser(): getForEntity() "+forEntity);
            // log.info("User Controller -> getUser(): getForEntity() hotel "+hotel);
            // set the hotel rating
            rating.setHotel(hotel);
            // return the rating
            return rating;
        }).toList();
        user.setRatingList(ratingList);
        return user;



    }
}
