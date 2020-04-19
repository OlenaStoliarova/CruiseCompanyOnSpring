package ua.cruise.company.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.cruise.company.entity.Ship;
import ua.cruise.company.repository.ShipRepository;
import ua.cruise.company.service.exception.NoEntityFoundException;
import ua.cruise.company.service.exception.NonUniqueObjectException;
import ua.cruise.company.service.exception.SomethingWentWrongException;

@Service
public class ShipService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShipService.class);

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

    public void create(Ship ship) throws NonUniqueObjectException, SomethingWentWrongException {
        try {
            ship = shipRepository.save(ship);

            if (ship.getId() == null)
                throw new SomethingWentWrongException("Ship wasn't saved");
        } catch (DataIntegrityViolationException exception) {
            LOGGER.error("Ship wasn't saved {}, {}", ship, exception.getMessage());
            throw new NonUniqueObjectException("Ship with such name already exists.");
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            throw new SomethingWentWrongException(ex.getMessage(), ex);
        }
    }
}
