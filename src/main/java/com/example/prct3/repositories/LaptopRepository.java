package com.example.prct3.repositories;

import com.example.prct3.models.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopRepository extends JpaRepository<Laptop, Long> {
    // Дополнительные методы для работы с ноутбуками (если необходимо)
}
