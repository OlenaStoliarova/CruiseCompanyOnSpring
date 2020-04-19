package ua.cruise.company.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.cruise.company.dto.CruiseDTO;
import ua.cruise.company.dto.converter.CruiseDTOConverter;
import ua.cruise.company.entity.Cruise;
import ua.cruise.company.repository.CruiseRepository;
import ua.cruise.company.service.exception.NonUniqueObjectException;
import ua.cruise.company.service.exception.SomethingWentWrongException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CruiseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CruiseService.class);

    @Autowired
    private CruiseRepository cruiseRepository;

    public Page<CruiseDTO> getAllCruisesFromToday(Pageable pageable) {
        Page<Cruise> cruises = cruiseRepository.findAllByStartingDateGreaterThanEqualAndVacanciesGreaterThanOrderByStartingDateAsc(LocalDate.now(), 0, pageable);
        List<CruiseDTO> curPageDTO = cruises.getContent().stream()
                .map(CruiseDTOConverter::convertToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(curPageDTO, pageable, cruises.getTotalElements());
    }

    public void create(Cruise cruise) throws NonUniqueObjectException, SomethingWentWrongException {
        try {
            cruise = cruiseRepository.save(cruise);

            if (cruise.getId() == null)
                throw new SomethingWentWrongException("Cruise wasn't saved");
        } catch (DataIntegrityViolationException exception) {
            LOGGER.error("Cruise wasn't saved {}, {}", cruise, exception.getMessage());
            throw new NonUniqueObjectException("Cruise with such ship already exists for selected date.");
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            throw new SomethingWentWrongException(ex.getMessage(), ex);
        }
    }
}
