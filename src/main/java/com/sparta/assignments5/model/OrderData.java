package com.sparta.assignments5.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class OrderData {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @Column(nullable = false)
        private String restaurantName;

        @OneToMany
        @JoinColumn(name = "ORDERFOODS_ID", nullable = false)
        private List<OrderFoods> foods;

        @Column(nullable = false)
        private int deliveryFee;

        @Column(nullable = false)
        private int totalPrice;
}
