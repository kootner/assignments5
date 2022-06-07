package com.sparta.assignments5.dto;

import com.sparta.assignments5.model.OrderData;
import com.sparta.assignments5.model.OrderFoods;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDto {

    private String restaurantName;
    private List<OrderFoods> foods;
    private int deliveryFee;
    private int totalPrice;


    public OrderDto(OrderData order) {
        this.restaurantName = order.getRestaurantName();
        this.foods = order.getFoods();
        this.deliveryFee = order.getDeliveryFee();
        this.totalPrice = order.getTotalPrice();
    }
}
