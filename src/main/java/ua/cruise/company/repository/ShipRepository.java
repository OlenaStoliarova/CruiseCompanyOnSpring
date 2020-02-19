package ua.cruise.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.cruise.company.entity.Ship;

import java.util.List;
import java.util.Optional;

public interface ShipRepository extends JpaRepository<Ship,Long> {
    List<Ship> findAllByOrderByName();

    List<Ship> findAllByIdIn(Iterable<Long> ids);

    Optional<Ship> findByName(String name);
}
