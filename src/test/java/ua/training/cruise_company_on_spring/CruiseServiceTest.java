package ua.training.cruise_company_on_spring;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.training.cruise_company_on_spring.entity.Cruise;
import ua.training.cruise_company_on_spring.entity.Ship;
import ua.training.cruise_company_on_spring.service.CruiseService;
import ua.training.cruise_company_on_spring.service.NoEntityFoundException;
import ua.training.cruise_company_on_spring.service.ShipService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Random;

@SpringBootTest
public class CruiseServiceTest {
    private static final Logger LOGGER= LoggerFactory.getLogger(CruiseServiceTest.class);

    @Autowired
    private CruiseService cruiseService;
    @Autowired
    private ShipService shipService;

    @Test
    void addCruises(){
        /*
        LocalDate startingDate = LocalDate.of(2020, Month.MARCH, 8);
        addScheduledCruises("Aegean Odyssey", startingDate, 7, new BigDecimal(800));*/

        LocalDate startingDate = LocalDate.of(2020, Month.APRIL, 2);
        addScheduledCruises("Magellan", startingDate, 10, new BigDecimal(1000));

        startingDate = LocalDate.of(2020, Month.MAY, 15);
        addScheduledCruises("Viking Star", startingDate, 14, new BigDecimal(1500));

        startingDate = LocalDate.of(2020, Month.MAY, 22);
        addScheduledCruises("Nordkapp", startingDate, 14, new BigDecimal(1200));

        startingDate = LocalDate.of(2020, Month.JUNE, 1);
        addScheduledCruises("Costa Mediterranea", startingDate, 10, new BigDecimal(1350));

        startingDate = LocalDate.of(2020, Month.JUNE, 7);
        addScheduledCruises("Monarch", startingDate, 10, new BigDecimal(1100));
    }

    private void addScheduledCruises(String shipName, LocalDate startingDate, int intervalDays, BigDecimal basicPrice){
        LOGGER.info("Adding cruises for ship" + shipName);

        Ship ship;
        try {
            ship = shipService.getShipByName(shipName);
        }
        catch (NoEntityFoundException ex){
            LOGGER.error("Ship '" + shipName + "' was not found");
            assert(false);
            return;
        }

        if( intervalDays < ship.getOneTripDurationDays()){
            LOGGER.error("Error creating scheduled cruises for ship '" + shipName + "'. Provided interval is less than oneTripDurationDays (" + ship.getOneTripDurationDays() + ")");
            assert(false);
            return;
        }

        boolean result;

        for(int i=0; i<8; i++){
            LocalDate cruiseDate = startingDate.plusDays(i * intervalDays);

            //Let's say final price can be from 1 to 1,5 of basicPrice
            BigDecimal price = basicPrice.multiply(BigDecimal.valueOf(GetRandomNumber(1.0, 1.5)));

            result = cruiseService.saveCruise(
                    Cruise.builder()
                            .startingDate(cruiseDate)
                            .ship(ship)
                            .vacancies(ship.getCapacity())
                            .price(price)
                            .build());
            assert (result);
        }

    }

    private double GetRandomNumber(double minimum, double maximum)
    {
        Random random = new Random();
        return random.nextDouble() * (maximum - minimum) + minimum;
    }
}
