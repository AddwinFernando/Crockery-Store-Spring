package com.restapi.repository;

import com.restapi.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus,Long> {
}
