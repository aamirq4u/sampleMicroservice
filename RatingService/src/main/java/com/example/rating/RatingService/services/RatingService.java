package com.example.rating.RatingService.services;

import com.example.rating.RatingService.entities.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {

    // create
    Rating create(Rating rating);

    // get all rating
    List<Rating> getAllRatings();

    // get all by userId
    List<Rating> getRatingByUserId(String userId);

    // get all by hotel
    List<Rating> getRatingByHotelId(String hotelId);
}
