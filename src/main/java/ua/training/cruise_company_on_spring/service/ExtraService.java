package ua.training.cruise_company_on_spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.training.cruise_company_on_spring.entity.Extra;
import ua.training.cruise_company_on_spring.repository.ExtraRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExtraService {
    private static final Logger LOGGER= LoggerFactory.getLogger(ExtraService.class);

    @Autowired
    private ExtraRepository extraRepository;

    public List<Extra> allExtras() { return extraRepository.findAllByOrderByNameEn();}

    public Extra findExtraById(Long id) throws NoEntityFoundException {
        return extraRepository.findById(id)
                .orElseThrow(() -> new NoEntityFoundException("There is no extra with provided id (" + id + ")"));
    }

    public Extra findExtraByNameEn(String name) throws NoEntityFoundException {
        return extraRepository.findByNameEn(name)
                .orElseThrow(() -> new NoEntityFoundException("There is no extra with provided name (" + name + ")"));
    }

    public boolean saveExtra(Extra extra) {
        try{
            extraRepository.save(extra);
        }catch (
                DataIntegrityViolationException exception){
            LOGGER.error("Ship's extra wasn't saved {}, {}", extra, exception.getMessage());
            return false;
        }
        return true;
    }

}
