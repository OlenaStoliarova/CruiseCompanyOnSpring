package ua.cruise.company.dto.converter;

import ua.cruise.company.dto.ExcursionDTO;
import ua.cruise.company.entity.Excursion;

public class ExcursionDTOConverter {

    public static ExcursionDTO convertToDTO(Excursion excursion) {
        ExcursionDTO dto = new ExcursionDTO();

        if(excursion == null){
            return dto;
        }

        dto.setId( excursion.getId() );
        setLocaleSpecificFields(dto, excursion);
        dto.setApproximateDurationHr( excursion.getApproximateDurationHr() );
        dto.setPrice( LocalizationHelper.getPriceInLocaleCurrency( excursion.getPriceUSD()));
        dto.setSeaport( SeaportDTOConverter.convertToDTO( excursion.getSeaport()));

        return dto;
    }

    private static void setLocaleSpecificFields(ExcursionDTO dto, Excursion excursion){
        if(LocalizationHelper.getCurrentLang().equalsIgnoreCase("uk")){
            dto.setName( excursion.getNameUkr());
            dto.setDescription( excursion.getDescriptionUkr());
            return;
        }

        dto.setName( excursion.getNameEn());
        dto.setDescription( excursion.getDescriptionEn());
    }
}
