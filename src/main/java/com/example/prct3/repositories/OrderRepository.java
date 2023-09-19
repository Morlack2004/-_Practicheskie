package com.example.prct3.repositories;

import com.example.prct3.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Дополнительные методы для работы с заказами (если необходимо)
}
