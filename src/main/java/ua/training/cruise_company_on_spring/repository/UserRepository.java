package ua.training.cruise_company_on_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import ua.training.cruise_company_on_spring.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
