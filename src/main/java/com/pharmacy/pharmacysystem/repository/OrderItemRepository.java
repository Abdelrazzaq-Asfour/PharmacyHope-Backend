package com.pharmacy.pharmacysystem.repository;

import com.pharmacy.pharmacysystem.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
