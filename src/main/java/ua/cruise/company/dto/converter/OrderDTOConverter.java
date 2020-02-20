package ua.cruise.company.dto.converter;

import ua.cruise.company.dto.OrderDTO;
import ua.cruise.company.entity.Excursion;
import ua.cruise.company.entity.Extra;
import ua.cruise.company.entity.Order;
import ua.cruise.company.entity.OrderStatus;

import java.util.Set;

public class OrderDTOConverter {

    public static OrderDTO convertToDTO(Order order) {
        OrderDTO dto = new OrderDTO();

        if(order == null){
            return dto;
        }

        dto.setId( order.getId() );
        dto.setCreationDate( order.getCreationDate() );
        dto.setUser( UserDTOConverter.convertToDTO( order.getUser() ));
        dto.setCruise( CruiseDTOConverter.convertToDTO( order.getCruise()));
        dto.setQuantity( order.getQuantity());
        dto.setTotalPrice( LocalizationHelper.getPriceInLocaleCurrency( order.getTotalPrice()));
        dto.setStatus( order.getStatus().toString() );
        dto.setAddedExcursions( convertOrderExcursionsToString(order));
        dto.setFreeExtras( convertOrderFreeExtrasToString(order));

        return dto;
    }

    private static String convertOrderExcursionsToString(Order order){
        String lang = LocalizationHelper.getCurrentLang();

        if( (order.getExcursions() != null) && (!order.getExcursions().isEmpty())){
            Set<Excursion> excursions = order.getExcursions();
            StringBuilder excursionsStr = new StringBuilder();
            for( Excursion excursion: excursions){
                if (lang.equalsIgnoreCase("uk"))
                    excursionsStr.append(excursion.getNameUkr());
                else
                    excursionsStr.append(excursion.getNameEn());
                excursionsStr.append(";\n");
            }
            excursionsStr.replace( excursionsStr.lastIndexOf(";\n"), excursionsStr.length(), "");

            return excursionsStr.toString();
        }

        try {
            if ( order.getStatus().compareTo(OrderStatus.EXCURSIONS_ADDED) >= 0) {
                return "-";
            }
        } catch (NullPointerException ignored){}

        return "";
    }

    private static String convertOrderFreeExtrasToString(Order order) {
        String lang = LocalizationHelper.getCurrentLang();

        if ( (order.getFreeExtras() != null) && (!order.getFreeExtras().isEmpty())) {
            Set<Extra> extras = order.getFreeExtras();
            StringBuilder extrasStr = new StringBuilder();
            for (Extra bonus : extras) {
                if (lang.equalsIgnoreCase("uk"))
                    extrasStr.append(bonus.getNameUkr());
                else
                    extrasStr.append(bonus.getNameEn());
                extrasStr.append(", ");
            }
            extrasStr.replace(extrasStr.lastIndexOf(","), extrasStr.length(), "");

            return extrasStr.toString();
        }

        if (order.getStatus() == OrderStatus.EXTRAS_ADDED) {
            return "-";
        }

        return "";
    }
}
