package ua.cruise.company.dto.converter;

import org.springframework.context.i18n.LocaleContextHolder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationHelper {
    public static final String MESSAGES_BUNDLE_NAME = "lang/messages";

    public static String getCurrentLang(){
        return LocaleContextHolder.getLocale().getLanguage();
    }

    public static BigDecimal getPriceInLocaleCurrency(BigDecimal priceInUSD){
        if(priceInUSD == null){
            return null;
        }

        BigDecimal localPriceMultiplier = new BigDecimal( getLocalizedMessage("localization.price.multiplier"));

        return localPriceMultiplier.multiply( priceInUSD );
    }


    public static String getLocalizedMessage(String messageName){
        Locale currentLocale = LocaleContextHolder.getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle(MESSAGES_BUNDLE_NAME, currentLocale);
        return bundle.getString(messageName);
    }
}
