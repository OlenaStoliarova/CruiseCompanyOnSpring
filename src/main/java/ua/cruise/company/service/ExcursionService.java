package ua.cruise.company.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.cruise.company.entity.Excursion;
import ua.cruise.company.repository.ExcursionRepository;
import ua.cruise.company.service.exception.NoEntityFoundException;
import ua.cruise.company.service.exception.NonUniqueObjectException;
import ua.cruise.company.service.exception.SomethingWentWrongException;

@Service
public class ExcursionService {
    private static final Logger LOGGER = LogManager.getLogger(ExcursionService.class);

    @Autowired
    private ExcursionRepository excursionRepository;

    public Page<Excursion> getAllExcursions(Pageable pageable) {
        return excursionRepository.findAllByOrderBySeaportNameEnAsc(pageable);
    }

    public Page<Excursion> getAllExcursionsInSeaport(Long seaportId, Pageable pageable) {
        return excursionRepository.findBySeaportId(seaportId, pageable);
    }

    public Excursion getExcursionById(Long id) throws NoEntityFoundException {
        return excursionRepository.findById(id)
                .orElseThrow(() -> new NoEntityFoundException("There is no excursion with provided id (" + id + ")"));
    }

    public void create(Excursion excursion) throws NonUniqueObjectException, SomethingWentWrongException {
        ensureExcursionIsNew(excursion);
        try {
            excursion = excursionRepository.save(excursion);

            if (excursion.getId() == null)
                throw new SomethingWentWrongException("Excursion wasn't saved");
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Error creating excursion {}. {}", excursion, e.getMessage());
            throw new NonUniqueObjectException("Excursion with such name already exists for this port");
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            throw new SomethingWentWrongException(ex.getMessage(), ex);
        }
    }

    public void edit(Excursion excursion) throws NonUniqueObjectException, SomethingWentWrongException {
        if (excursion.getId() == null) {
            LOGGER.error("Attempt to edit excursion with empty id field");
            throw new SomethingWentWrongException("Attempt to edit excursion with empty id field");
        }

        try {
            excursionRepository.save(excursion);
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Error editing excursion {}. {}", excursion, e.getMessage());
            throw new NonUniqueObjectException("Excursion with such name already exists for this port");
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            throw new SomethingWentWrongException(ex.getMessage(), ex);
        }
    }

    public void deleteExcursion(Long excursionId) {
        excursionRepository.deleteById(excursionId);
    }


    private void ensureExcursionIsNew(Excursion excursion) {
        if (excursion.getId() != null) {
            excursion.setId(null);
        }
    }
}
