package ua.training.cruise_company_on_spring.dto.utility;

import ua.training.cruise_company_on_spring.entity.Excursion;
import ua.training.cruise_company_on_spring.entity.Extra;
import ua.training.cruise_company_on_spring.entity.Order;
import ua.training.cruise_company_on_spring.entity.OrderStatus;

import java.util.Set;

public class OrderDTOConvertUtils {

    public static String convertOrderExcursionsToString(Order order, String lang){
        if( !order.getExcursions().isEmpty()){
            Set<Excursion> excursions = order.getExcursions();
            StringBuilder excursionsStr = new StringBuilder();
            for( Excursion excursion: excursions){
                if (lang.equalsIgnoreCase("ukr"))
                    excursionsStr.append(excursion.getNameUkr());
                else
                    excursionsStr.append(excursion.getNameEn());
                excursionsStr.append(", ");
            }
            excursionsStr.replace( excursionsStr.lastIndexOf(","), excursionsStr.length(), "");

            return excursionsStr.toString();
        } else if (order.getStatus().compareTo(OrderStatus.EXCURSIONS_ADDED) >= 0){
            return "-";
        }
        return "";
    }

    public static String convertOrderFreeExtrasToString(Order order, String lang) {
        if (!order.getFreeExtras().isEmpty()) {
            Set<Extra> extras = order.getFreeExtras();
            StringBuilder extrasStr = new StringBuilder();
            for (Extra bonus : extras) {
                if (lang.equalsIgnoreCase("ukr"))
                    extrasStr.append(bonus.getNameUkr());
                else
                    extrasStr.append(bonus.getNameEn());
                extrasStr.append(", ");
            }
            extrasStr.replace(extrasStr.lastIndexOf(","), extrasStr.length(), "");

            return extrasStr.toString();
        } else if (order.getStatus() == OrderStatus.EXTRAS_ADDED) {
            return "-";
        }
        return "";
    }
}
