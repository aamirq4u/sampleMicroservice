package com.example.hotel.HotelService.services;

import com.example.hotel.HotelService.entities.Hotel;

import java.util.List;

public interface HotelServices {

    // create
    Hotel create(Hotel hotel);

    // get all
    List<Hotel> getAllHotel();

    // get single hotel
    Hotel getSingleHotel(String id);
}
