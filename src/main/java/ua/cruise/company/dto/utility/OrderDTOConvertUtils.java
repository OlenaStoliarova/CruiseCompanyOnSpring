package ua.cruise.company.dto.utility;

import ua.cruise.company.entity.Excursion;
import ua.cruise.company.entity.Extra;
import ua.cruise.company.entity.Order;
import ua.cruise.company.entity.OrderStatus;

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
