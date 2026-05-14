package com.example.hotel.HotelService.controller;

import com.example.hotel.HotelService.entities.Hotel;
import com.example.hotel.HotelService.services.HotelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelServices userService;

    //create
    @PostMapping
    public ResponseEntity<Hotel> createUser(@RequestBody Hotel hotel){
        Hotel hotel1 = userService.create(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }
    //single hotel get

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getSingleUser(@PathVariable String hotelId){
        Hotel hotel = userService.getSingleHotel(hotelId);
        return ResponseEntity.ok(hotel);
    }

    // all hotel get
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllUser(){
        return ResponseEntity.ok(userService.getAllHotel());
    }
}
