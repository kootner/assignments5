package com.sparta.assignments5.service;

import com.sparta.assignments5.dto.RestaurantRequestDto;
import com.sparta.assignments5.model.Restaurant;
import com.sparta.assignments5.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;


    public Restaurant registerRestaurant(RestaurantRequestDto requestDto) {

        if (requestDto.getMinOrderPrice() < 1000 || 100000 < requestDto.getMinOrderPrice())
            throw new IllegalArgumentException("허용값이 아닙니다.");
        if (requestDto.getMinOrderPrice() % 100 != 0)
            throw new IllegalArgumentException("100원 단위로 입력이 가능합니다.");
        if (requestDto.getDeliveryFee() < 0 || 10000 < requestDto.getDeliveryFee())
            throw new IllegalArgumentException("허용값이 아닙니다.");
        if (requestDto.getDeliveryFee() % 500 != 0)
            throw new IllegalArgumentException("500원 단위로 입력이 가능합니다.");

        Restaurant restaurant = new Restaurant(requestDto);

        return restaurantRepository.save(restaurant);

    }
}