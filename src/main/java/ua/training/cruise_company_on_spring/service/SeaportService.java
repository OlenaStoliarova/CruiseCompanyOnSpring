package ua.training.cruise_company_on_spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.training.cruise_company_on_spring.entity.Seaport;
import ua.training.cruise_company_on_spring.repository.SeaportRepository;

import java.util.List;

@Service
public class SeaportService {
    private static final Logger LOGGER= LoggerFactory.getLogger(SeaportService.class);

    @Autowired
    SeaportRepository seaportRepository;

    public List<Seaport> allPorts() {
        return seaportRepository.findAllByOrderByNameEnAsc();
    }

    public Seaport findPortByNameEn(String nameEn) throws NoEntityFoundException {

        return seaportRepository.findByNameEn(nameEn)
                                .orElseThrow(() -> new NoEntityFoundException("There is no port with provided name (" + nameEn + ")"));
    }

    public Seaport findPortById (Long portId) throws NoEntityFoundException {
        return seaportRepository.findById(portId)
                                .orElseThrow(() -> new NoEntityFoundException("There is no port with provided id (" + portId + ")"));
    }

    public boolean savePort(Seaport seaport) {
        try{
            seaportRepository.save(seaport);
        }catch (DataIntegrityViolationException exception){
            LOGGER.error("Seaport wasn't saved {}, {}", seaport, exception.getMessage());
            return false;
        }
        return true;
    }
}
