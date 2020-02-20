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

import java.util.List;

@Service
public class ExcursionService {
    private static final Logger LOGGER = LogManager.getLogger(ExcursionService.class);

    @Autowired
    private ExcursionRepository excursionRepository;

    public List<Excursion> allExcursions(){
        return excursionRepository.findAllByOrderBySeaportNameEnAsc();
    }

    public Page<Excursion> allExcursions(Pageable pageable){
        return excursionRepository.findAllByOrderBySeaportNameEnAsc(pageable);
    }

    public Page<Excursion> allExcursionsInSeaport(Long seaportId, Pageable pageable){
        return excursionRepository.findBySeaportId(seaportId, pageable);
    }

    public Excursion getExcursionById(Long id) throws NoEntityFoundException {
        return excursionRepository.findById(id)
                .orElseThrow(() -> new NoEntityFoundException("There is no excursion with provided id (" + id + ")"));
    }

    public boolean createExcursion(Excursion excursion) throws NonUniqueObjectException {
        if(excursion.getId() != null){
            excursion.setId(null);
        }

        LOGGER.info("Creating excursion " + excursion);
        try{
            excursion = excursionRepository.save(excursion);
        } catch(DataIntegrityViolationException e){
            LOGGER.info(e.getMessage(), e);
            throw new NonUniqueObjectException("Excursion with such name already exists for this port");
        }catch(Exception ex){
            LOGGER.info(ex.getMessage(), ex);
            return false;
        }

        boolean isCreated = excursion.getId() != null;
        if( isCreated){
            LOGGER.info("Excursion was created successfully");
        }
        return isCreated;
    }

    public boolean editExcursion(Excursion excursion) throws NonUniqueObjectException {
        if ( excursion.getId() == null) {
            LOGGER.error("Attempt to edit excursion with empty id field");
            return false;
        }

        LOGGER.info("Editing excursion " + excursion);
        try{
            excursionRepository.save(excursion);
            return true;
        } catch(DataIntegrityViolationException e){
            LOGGER.info(e.getMessage(), e);
            throw new NonUniqueObjectException("Excursion with such name already exists for this port");
        }catch(Exception ex){
            LOGGER.info(ex.getMessage(), ex);
            return false;
        }
    }

    public void deleteExcursion(Long excursionId){
        excursionRepository.deleteById(excursionId);
    }
}
