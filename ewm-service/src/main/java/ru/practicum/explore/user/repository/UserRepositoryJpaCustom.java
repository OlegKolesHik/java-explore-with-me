package ru.practicum.explore.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.explore.user.model.User;

public interface UserRepositoryJpaCustom extends JpaRepository<User, Long> {
    @Query("SELECT COUNT (u) FROM User u WHERE u.name LIKE ?1")
    Integer findAllByName(String name);
}