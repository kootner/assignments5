package com.sparta.assignments5.repository;

import com.sparta.assignments5.model.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDataRepository extends JpaRepository <OrderData, Long> {
}
