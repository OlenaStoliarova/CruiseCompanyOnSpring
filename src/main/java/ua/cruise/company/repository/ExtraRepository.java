package ua.cruise.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.cruise.company.entity.Extra;

import java.util.List;
import java.util.Optional;

public interface ExtraRepository extends JpaRepository<Extra, Long> {
    List<Extra> findAllByOrderByNameEn();

    Optional<Extra> findByNameEn(String nameEn);
}
