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

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CruiseService {
    private static final Logger LOGGER= LoggerFactory.getLogger(CruiseService.class);

    @Autowired
    private CruiseRepository cruiseRepository;

    public boolean saveCruise(Cruise cruise){
        try{
            cruiseRepository.save(cruise);
        }catch (DataIntegrityViolationException exception){
            LOGGER.error("Cruise wasn't saved {}, {}", cruise, exception.getMessage());
            return false;
        }
        return true;
    }

    public Page<CruiseDTO> allCruisesFromTodayPaginated(Pageable pageable) {
        Page<Cruise> cruises = cruiseRepository.findAllByStartingDateGreaterThanEqualAndVacanciesGreaterThanOrderByStartingDateAsc(LocalDate.now(), 0, pageable);
        List<CruiseDTO> curPageDTO = cruises.getContent().stream()
                .map(CruiseDTOConverter::convertToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(curPageDTO, pageable, cruises.getTotalElements());
    }
}
