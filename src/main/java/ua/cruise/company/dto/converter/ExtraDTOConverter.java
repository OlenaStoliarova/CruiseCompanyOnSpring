package ua.cruise.company.dto.converter;

import ua.cruise.company.dto.ExtraDTO;
import ua.cruise.company.entity.Extra;

public class ExtraDTOConverter {

    public static ExtraDTO convertToDTO(Extra extra){
        if(extra == null){
            return new ExtraDTO();
        }

        if(LocalizationHelper.getCurrentLang().equalsIgnoreCase("uk"))
            return new ExtraDTO( extra.getId(),  extra.getNameUkr());

        return new ExtraDTO( extra.getId(),  extra.getNameEn());
    }
}
