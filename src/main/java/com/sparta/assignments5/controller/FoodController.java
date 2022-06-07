package com.sparta.assignments5.controller;

import com.sparta.assignments5.dto.FoodRequestDto;
import com.sparta.assignments5.model.Food;
import com.sparta.assignments5.repository.FoodRepository;
import com.sparta.assignments5.service.FoodService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class FoodController {

    private final FoodService foodService;
    private final FoodRepository foodRepository;


    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void foodRegister(@PathVariable Long restaurantId, @RequestBody List<FoodRequestDto> requestDto){
        foodService.foodRegister(requestDto,restaurantId);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> foodMenu(@PathVariable Long restaurantId) {
        return foodRepository.findAllByRestaurantId(restaurantId);
    }
}
