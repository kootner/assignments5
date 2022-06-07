package com.sparta.assignments5.controller;

import com.sparta.assignments5.dto.RestaurantRequestDto;
import com.sparta.assignments5.model.Restaurant;
import com.sparta.assignments5.repository.RestaurantRepository;
import com.sparta.assignments5.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;

    @PostMapping("/restaurant/register")
    public Restaurant registerRestaurant(@RequestBody RestaurantRequestDto requestDto) {
        return restaurantService.registerRestaurant(requestDto);
    }

    @GetMapping("/restaurants")
    public List<Restaurant> searchRestaurant() {
        return restaurantRepository.findAll();
    }


}
