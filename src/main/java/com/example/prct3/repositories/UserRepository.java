package com.example.prct3.repositories;

import com.example.prct3.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Здесь можно добавить дополнительные методы для работы с базой данных, если необходимо
}
