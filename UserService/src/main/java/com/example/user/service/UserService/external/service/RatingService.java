package com.example.user.service.UserService.external.service;

import com.example.user.service.UserService.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @GetMapping("/ratings//users/{userId}")
    Rating[] getRating(@PathVariable String userId);
}
