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

        orderData.setRestaurantName(restaurant.getName());
        orderData.setDeliveryFee(restaurant.getDeliveryFee());

        for (OrderRequestFoodsDto orderRequestFoodsDto : foods) {
            if (orderRequestFoodsDto.getQuantity() <= 0 || orderRequestFoodsDto.getQuantity() > 100)
                throw new IllegalArgumentException("허용값이 아닙니다.");

            Long id = orderRequestFoodsDto.getId();
            Food food = foodRepository.findById(id).orElseThrow(() ->
                    new IllegalArgumentException("음식 ID 값이 없습니다."));

            OrderFoods orderFoods = new OrderFoods();
            orderFoods.setName(food.getName());
            orderFoods.setQuantity(orderRequestFoodsDto.getQuantity());
            orderFoods.setPrice(food.getPrice() * orderRequestFoodsDto.getQuantity());

            orderFoodsList.add(orderFoods);
            totalPrice += orderFoods.getPrice();
        }
        orderData.setFoods(orderFoodsList);

        if (restaurant.getMinOrderPrice() > totalPrice)
            throw new IllegalArgumentException("주문 금액이 최소 주문 금액 보다 낮습니다.");
        orderData.setTotalPrice(totalPrice + orderData.getDeliveryFee());
        orderRepository.save(orderData);

        return new OrderDto(orderData);
    }

}
