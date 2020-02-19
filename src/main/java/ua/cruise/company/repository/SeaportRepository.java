package ua.cruise.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.cruise.company.entity.Seaport;

import java.util.List;
import java.util.Optional;

public interface SeaportRepository extends JpaRepository<Seaport, Long> {
    List<Seaport> findAllByOrderByNameEnAsc();

    Optional<Seaport> findByNameEn(String nameEn);
}
