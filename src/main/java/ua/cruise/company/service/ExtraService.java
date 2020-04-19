package ua.cruise.company.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.cruise.company.entity.Extra;
import ua.cruise.company.repository.ExtraRepository;
import ua.cruise.company.service.exception.NoEntityFoundException;
import ua.cruise.company.service.exception.NonUniqueObjectException;
import ua.cruise.company.service.exception.SomethingWentWrongException;

import java.util.List;

@Service
public class ExtraService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExtraService.class);

    @Autowired
    private ExtraRepository extraRepository;

    public List<Extra> getAllExtras() {
        return extraRepository.findAllByOrderByNameEn();
    }

    public Extra findExtraById(Long id) throws NoEntityFoundException {
        return extraRepository.findById(id)
                .orElseThrow(() -> new NoEntityFoundException("There is no extra with provided id (" + id + ")"));
    }

    public Extra findExtraByNameEn(String name) throws NoEntityFoundException {
        return extraRepository.findByNameEn(name)
                .orElseThrow(() -> new NoEntityFoundException("There is no extra with provided name (" + name + ")"));
    }

    public void create(Extra extra) throws NonUniqueObjectException, SomethingWentWrongException {
        try {
            extra = extraRepository.save(extra);

            if (extra.getId() == null)
                throw new SomethingWentWrongException("Extra wasn't saved");
        } catch (DataIntegrityViolationException exception) {
            LOGGER.error("Ship's extra wasn't saved {}, {}", extra, exception.getMessage());
            throw new NonUniqueObjectException("Extra with such name already exists.");
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            throw new SomethingWentWrongException(ex.getMessage(), ex);
        }
    }
}
