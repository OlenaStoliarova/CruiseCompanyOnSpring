package ua.cruise.company.dto.converter;

import ua.cruise.company.dto.CruiseDTO;
import ua.cruise.company.entity.Cruise;

import java.time.LocalDate;

public class CruiseDTOConverter {

    public static CruiseDTO convertToDTO(Cruise cruise) {
        CruiseDTO dto = new CruiseDTO();

        if(cruise == null){
            return dto;
        }

        dto.setId( cruise.getId() );
        dto.setStartingDate( cruise.getStartingDate());
        dto.setFinishingDate( calculateFinishingDate(cruise));
        dto.setVacancies( cruise.getVacancies());
        dto.setPrice( LocalizationHelper.getPriceInLocaleCurrency( cruise.getPrice()));
        dto.setShip( ShipDTOConverter.convertToDTO( cruise.getShip() ));

        return dto;
    }

    private static LocalDate calculateFinishingDate(Cruise cruise){
        try {
            long tripDuration = cruise.getShip().getOneTripDurationDays();
            return cruise.getStartingDate().plusDays(tripDuration);
        } catch (NullPointerException ignored){
            return null;
        }
    }
}
