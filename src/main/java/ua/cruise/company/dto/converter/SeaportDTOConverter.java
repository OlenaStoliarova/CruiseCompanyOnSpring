package ua.cruise.company.dto.converter;

import ua.cruise.company.dto.SeaportDTO;
import ua.cruise.company.entity.Seaport;

public class SeaportDTOConverter {

    public static SeaportDTO convertToDTO(Seaport seaport){
        if(seaport == null){
            return new SeaportDTO();
        }

        if(LocalizationHelper.getCurrentLang().equalsIgnoreCase("uk"))
            return new SeaportDTO( seaport.getId(),  seaport.getNameUkr(),  seaport.getCountryUkr());

        return new SeaportDTO( seaport.getId(),  seaport.getNameEn(),  seaport.getCountryEn());
    }
}
