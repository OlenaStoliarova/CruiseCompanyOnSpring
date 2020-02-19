package ua.cruise.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.cruise.company.entity.Excursion;
import ua.cruise.company.repository.ExcursionRepository;
import ua.cruise.company.service.exception.NoEntityFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ExcursionService {
    @Autowired
    private ExcursionRepository excursionRepository;

    public List<Excursion> allExcursions(){
        return excursionRepository.findAllByOrderBySeaportNameEnAsc();
    }

    public List<Excursion> allExcursionsInSeaport(Long seaportId){
        return excursionRepository.findBySeaportId(seaportId);
    }

    public Excursion getExcursionById(Long id) throws NoEntityFoundException {
        return excursionRepository.findById(id)
                .orElseThrow(() -> new NoEntityFoundException("There is no excursion with provided id (" + id + ")"));
    }

    public boolean saveExcursion(Excursion excursion) {
        if ( excursion.getId() == null) {
            //adding new excursion
            Optional<Excursion> excursionFromDB = excursionRepository.findByNameEnAndSeaport(excursion.getNameEn(), excursion.getSeaport());
            if (excursionFromDB.isPresent()) {
                return false;
            }
            excursionRepository.save(excursion);
            return true;
        } else{
            //editing existing excursion
            Optional<Excursion> excursionFromDB = excursionRepository.findById(excursion.getId());
            if (excursionFromDB.isPresent()) {
                excursionRepository.save(excursion);
                return true;
            }
            return false;
        }
    }

    public void deleteExcursion(Long excursionId){
        excursionRepository.deleteById(excursionId);
    }
}
