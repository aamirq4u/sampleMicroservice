package com.example.hotel.HotelService.impl;

import com.example.hotel.HotelService.entities.Hotel;
import com.example.hotel.HotelService.exception.ResourceNotFoundException;
import com.example.hotel.HotelService.repositories.HotelRepo;
import com.example.hotel.HotelService.services.HotelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelServices {

    @Autowired
    private HotelRepo hotelRepo;

    @Override
    public Hotel create(Hotel hotel) {
        String randomHotelId = UUID.randomUUID().toString();
        hotel.setId(randomHotelId);
        return hotelRepo.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotel() {
        return hotelRepo.findAll();
    }

    @Override
    public Hotel getSingleHotel(String id) {
        return hotelRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Hotel with given id not found on server !!! : "));
    }
}
