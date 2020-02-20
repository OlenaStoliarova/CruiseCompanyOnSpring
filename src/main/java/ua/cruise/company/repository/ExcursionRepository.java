package ua.cruise.company.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.cruise.company.entity.Excursion;

import java.util.List;

public interface ExcursionRepository extends JpaRepository<Excursion, Long> {
    List<Excursion> findAllByOrderBySeaportNameEnAsc();
    Page<Excursion> findAllByOrderBySeaportNameEnAsc(Pageable pageable);

    List<Excursion> findBySeaportId(Long seaportId);
    Page<Excursion> findBySeaportId(Long seaportId, Pageable pageable);

    List<Excursion> findBySeaport_IdIn(Iterable<Long> ids);
    List<Excursion> findByIdIn(Iterable<Long> ids);
}
