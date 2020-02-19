package ua.cruise.company.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.cruise.company.entity.Cruise;
import ua.cruise.company.repository.CruiseRepository;

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
