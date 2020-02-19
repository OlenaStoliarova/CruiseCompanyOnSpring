package ua.cruise.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.cruise.company.entity.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser_IdOrderByCreationDateDesc(Long userId);

    List<Order> findAllByOrderByCreationDateDesc();
}
