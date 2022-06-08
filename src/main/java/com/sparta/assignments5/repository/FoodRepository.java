package com.sparta.assignments5.repository;

import com.sparta.assignments5.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository <Food, Long> {
    List<Food> findAllByRestaurantId(Long restaurantId);
    Food findByRestaurantIdAndName(Long restaurantId,String name);
}
