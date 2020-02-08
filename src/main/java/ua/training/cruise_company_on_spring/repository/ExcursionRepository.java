package ua.training.cruise_company_on_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.training.cruise_company_on_spring.entity.Excursion;
import ua.training.cruise_company_on_spring.entity.Seaport;

import java.util.List;
import java.util.Optional;

public interface ExcursionRepository extends JpaRepository<Excursion, Long> {
    List<Excursion> findAllByOrderBySeaportNameEnAsc();

    List<Excursion> findBySeaportId(Long seaportId);
    Optional<Excursion> findByNameEnAndSeaport(String nameEn, Seaport seaport);

    List<Excursion> findBySeaport_IdIn(Iterable<Long> ids);
    List<Excursion> findByIdIn(Iterable<Long> ids);
}
