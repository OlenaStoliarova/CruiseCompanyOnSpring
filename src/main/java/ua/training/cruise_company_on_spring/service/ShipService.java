package ua.training.cruise_company_on_spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.training.cruise_company_on_spring.entity.Ship;
import ua.training.cruise_company_on_spring.repository.ShipRepository;

@Service
public class ShipService {
    private static final Logger LOGGER= LoggerFactory.getLogger(ShipService.class);

    @Autowired
    private ShipRepository shipRepository;

    public Ship getShipById(Long id) throws NoEntityFoundException {
        return shipRepository.findById(id)
                            .orElseThrow(() -> new NoEntityFoundException("There is no ship with provided id (" + id + ")"));
    }

    public Ship getShipByName(String name) throws NoEntityFoundException {
        return shipRepository.findByName(name)
                .orElseThrow(() -> new NoEntityFoundException("There is no ship with provided name (" + name + ")"));
    }

    public boolean saveShip(Ship ship){
        try{
            shipRepository.save(ship);
        }catch (DataIntegrityViolationException exception){
            LOGGER.error("Ship wasn't saved {}, {}", ship, exception.getMessage());
            return false;
        }
        return true;
    }
}
