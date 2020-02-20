package ua.cruise.company.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.cruise.company.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByUser_IdOrderByCreationDateDesc(Long userId, Pageable pageable);

    Page<Order> findAllByOrderByCreationDateDesc(Pageable pageable);
}
