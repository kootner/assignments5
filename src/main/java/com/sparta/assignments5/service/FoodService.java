package com.sparta.assignments5.service;

import com.sparta.assignments5.dto.FoodRequestDto;
import com.sparta.assignments5.model.Food;
import com.sparta.assignments5.repository.FoodRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
public class FoodService {

    private final FoodRepository foodRepository;

    @Transactional
    public void foodRegister(List<FoodRequestDto> requestDtos, @PathVariable Long restaurantId) {
        for (FoodRequestDto requestDto : requestDtos) {

            if (requestDto.getPrice() < 1000 || 1000000 < requestDto.getPrice())
                throw new IllegalArgumentException("허용값이 아닙니다.");
            if (requestDto.getPrice() % 100 != 0)
                throw new IllegalArgumentException("100원 단위로 입력이 가능합니다.");
            if (foodRepository.findByRestaurantIdAndName(restaurantId, requestDto.getName()) != null)
                throw new IllegalArgumentException("같은 음식점 내에서는 음식 이름이 중복 될 수 없습니다.");

            Food food = new Food();
            food.setName(requestDto.getName());
            food.setPrice(requestDto.getPrice());
            food.setRestaurantId(restaurantId);
            foodRepository.save(food);
        }
    }

}
