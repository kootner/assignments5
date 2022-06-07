package com.sparta.assignments5.service;

import com.sparta.assignments5.dto.OrderDto;
import com.sparta.assignments5.dto.OrderRequestDto;
import com.sparta.assignments5.dto.OrderRequestFoodsDto;
import com.sparta.assignments5.model.Food;
import com.sparta.assignments5.model.OrderData;
import com.sparta.assignments5.model.OrderFoods;
import com.sparta.assignments5.model.Restaurant;
import com.sparta.assignments5.repository.FoodRepository;
import com.sparta.assignments5.repository.OrderDataRepository;
import com.sparta.assignments5.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {

    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final OrderDataRepository orderRepository;


    public OrderDto orderRequest(OrderRequestDto orderRequestDto) {
        int totalPrice = 0;
        OrderData orderData = new OrderData();
        List<OrderFoods> orderFoodsList = new ArrayList<>();
        List<OrderRequestFoodsDto> foods = orderRequestDto.getFoods();
        Restaurant restaurant = restaurantRepository.getReferenceById(orderRequestDto.getRestaurantId());
        List<Food> foodList = foodRepository.findAllByRestaurantId(orderRequestDto.getRestaurantId());

        orderData.setRestaurantName(restaurant.getName());
        orderData.setDeliveryFee(restaurant.getDeliveryFee());

        for (int i = 0; i < orderRequestDto.getFoods().size(); i++) {
            if (foods.get(i).getQuantity() <= 0 || foods.get(i).getQuantity() > 100)
                throw new IllegalArgumentException("허용값이 아닙니다.");

            OrderFoods orderFoods = new OrderFoods();
            orderFoods.setId((long) i + 1);
            orderFoods.setName(foodList.get(i).getName());
            orderFoods.setQuantity(foods.get(i).getQuantity());
            orderFoods.setPrice(foodList.get(i).getPrice() * foods.get(i).getQuantity());

            orderFoodsList.add(orderFoods);
            totalPrice += orderFoods.getPrice();
        }
        orderData.setFoods(orderFoodsList);

        orderData.setTotalPrice(totalPrice + orderData.getDeliveryFee());
        orderRepository.save(orderData);

        return new OrderDto(orderData);
    }

}
