package ua.cruise.company.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.cruise.company.entity.Seaport;
import ua.cruise.company.repository.SeaportRepository;
import ua.cruise.company.service.exception.NoEntityFoundException;
import ua.cruise.company.service.exception.NonUniqueObjectException;
import ua.cruise.company.service.exception.SomethingWentWrongException;

import java.util.List;

@Service
public class SeaportService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeaportService.class);

    @Autowired
    SeaportRepository seaportRepository;

    public List<Seaport> getAllPorts() {
        return seaportRepository.findAllByOrderByNameEnAsc();
    }

    public Seaport getPortById(Long portId) throws NoEntityFoundException {
        return seaportRepository.findById(portId)
                .orElseThrow(() -> new NoEntityFoundException("There is no port with provided id (" + portId + ")"));
    }

    public void create(Seaport seaport) throws NonUniqueObjectException, SomethingWentWrongException {
        try {
            seaport = seaportRepository.save(seaport);

            if (seaport.getId() == null)
                throw new SomethingWentWrongException("Seaport wasn't saved");
        } catch (DataIntegrityViolationException exception) {
            LOGGER.error("Seaport wasn't saved {}, {}", seaport, exception.getMessage());
            throw new NonUniqueObjectException("Seaport with such name already exists.");
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            throw new SomethingWentWrongException(ex.getMessage(), ex);
        }
    }

}
