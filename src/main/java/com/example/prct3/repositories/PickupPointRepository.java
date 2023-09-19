package com.example.prct3.repositories;

import com.example.prct3.models.PickupPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PickupPointRepository extends JpaRepository<PickupPoint, Long> {
    // Дополнительные методы для работы с пунктами выдачи (если необходимо)
}
