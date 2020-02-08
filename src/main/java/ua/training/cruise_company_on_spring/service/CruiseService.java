package ua.training.cruise_company_on_spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.training.cruise_company_on_spring.entity.Cruise;
import ua.training.cruise_company_on_spring.repository.CruiseRepository;

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
}
