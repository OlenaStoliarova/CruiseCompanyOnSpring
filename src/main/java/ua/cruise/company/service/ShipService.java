package ua.cruise.company.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.cruise.company.entity.Ship;
import ua.cruise.company.repository.ShipRepository;
import ua.cruise.company.service.exception.NoEntityFoundException;

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
