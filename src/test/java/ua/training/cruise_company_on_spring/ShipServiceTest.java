package ua.training.cruise_company_on_spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.training.cruise_company_on_spring.entity.Extra;
import ua.training.cruise_company_on_spring.entity.Seaport;
import ua.training.cruise_company_on_spring.entity.Ship;
import ua.training.cruise_company_on_spring.service.ExtraService;
import ua.training.cruise_company_on_spring.service.NoEntityFoundException;
import ua.training.cruise_company_on_spring.service.SeaportService;
import ua.training.cruise_company_on_spring.service.ShipService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class ShipServiceTest {
    @Autowired
    private ShipService shipService;
    @Autowired
    private SeaportService seaportService;
    @Autowired
    private ExtraService extraService;

    @Test
    void addShips(){
        //addAegeanOdyssey();

        addMagellan();
        addVikingStar();
        addNordkapp();
        addCostaMediterranea();
        addMonarch();
    }

    private void addAegeanOdyssey(){
        Seaport port1, port2, port3;
        try {
            port1 = seaportService.findPortByNameEn("Izmir");
            port2 = seaportService.findPortByNameEn("Athens");
            port3 = seaportService.findPortByNameEn("Thessaloniki");
        }
        catch (NoEntityFoundException e){
            assert(false);
            return;
        }

        Set<Extra> extras = new HashSet<>();
        try {
            extras.add(extraService.findExtraByNameEn("Theater"));
            extras.add(extraService.findExtraByNameEn("Disco"));
            extras.add(extraService.findExtraByNameEn("Beauty salon"));
            extras.add(extraService.findExtraByNameEn("Sauna"));
            extras.add(extraService.findExtraByNameEn("Jacuzzi"));
            extras.add(extraService.findExtraByNameEn("Gym"));
            extras.add(extraService.findExtraByNameEn("SPA"));
            extras.add(extraService.findExtraByNameEn("Swimming pool"));
        }
        catch (NoEntityFoundException e){
            assert(false);
            return;
        }

        boolean result = shipService.saveShip(Ship.builder()
                .name("Aegean Odyssey")
                .capacity(380)
                .routeNameEn("Aegean Sea").routeNameUkr("Егейське море")
                .oneTripDurationDays(6)
                .visitingPorts(new HashSet<>(Arrays.asList(port1, port2, port3)))
                .extras(extras)
                .build());
        assert (result);
    }

    private void addMagellan(){
        Seaport port1, port2, port3;
        try {
            port1 = seaportService.findPortByNameEn("Barcelona");
            port2 = seaportService.findPortByNameEn("Valencia");
            port3 = seaportService.findPortByNameEn("Palma de Mallorca");
        }
        catch (NoEntityFoundException e){
            assert(false);
            return;
        }

        Set<Extra> extras = new HashSet<>();
        try {
            extras.add(extraService.findExtraByNameEn("Theater"));
            extras.add(extraService.findExtraByNameEn("Disco"));
            extras.add(extraService.findExtraByNameEn("Casino"));
            extras.add(extraService.findExtraByNameEn("Beauty salon"));
            extras.add(extraService.findExtraByNameEn("SPA"));
            extras.add(extraService.findExtraByNameEn("Sauna"));
            extras.add(extraService.findExtraByNameEn("Swimming pool"));
            extras.add(extraService.findExtraByNameEn("Jacuzzi"));
            extras.add(extraService.findExtraByNameEn("Gym"));
            extras.add(extraService.findExtraByNameEn("Children room"));
            extras.add(extraService.findExtraByNameEn("Children's pool"));
        }
        catch (NoEntityFoundException e){
            assert(false);
            return;
        }

        boolean result = shipService.saveShip(Ship.builder()
                .name("Magellan")
                .capacity(1452)
                .routeNameEn("Vamonos").routeNameUkr("Вамонос")
                .oneTripDurationDays(8)
                .visitingPorts(new HashSet<>(Arrays.asList(port1, port2, port3)))
                .extras(extras)
                .build());
        assert (result);
    }

    private void addVikingStar(){
        Seaport port1, port2, port3;
        try {
            port1 = seaportService.findPortByNameEn("Oslo");
            port2 = seaportService.findPortByNameEn("Copenhagen");
            port3 = seaportService.findPortByNameEn("Stockholm");
        }
        catch (NoEntityFoundException e){
            assert(false);
            return;
        }

        Set<Extra> extras = new HashSet<>();
        try {
            extras.add(extraService.findExtraByNameEn("Gym"));
            extras.add(extraService.findExtraByNameEn("Cinema hall"));
            extras.add(extraService.findExtraByNameEn("Theater"));
            extras.add(extraService.findExtraByNameEn("Disco"));
            extras.add(extraService.findExtraByNameEn("Sauna"));
            extras.add(extraService.findExtraByNameEn("Jacuzzi"));
            extras.add(extraService.findExtraByNameEn("Mini golf"));
        }
        catch (NoEntityFoundException e){
            assert(false);
            return;
        }

        boolean result = shipService.saveShip(Ship.builder()
                .name("Viking Star")
                .capacity(920)
                .routeNameEn("Three northern capitals").routeNameUkr("Три північні столиці")
                .oneTripDurationDays(12)
                .visitingPorts(new HashSet<>(Arrays.asList(port1, port2, port3)))
                .extras(extras)
                .build());
        assert (result);
    }

    private void addNordkapp(){
        Seaport port1, port2, port3;
        try {
            port1 = seaportService.findPortByNameEn("Oslo");
            port2 = seaportService.findPortByNameEn("Copenhagen");
            port3 = seaportService.findPortByNameEn("Stockholm");
        }
        catch (NoEntityFoundException e){
            assert(false);
            return;
        }

        Set<Extra> extras = new HashSet<>();
        try {
            extras.add(extraService.findExtraByNameEn("Cinema hall"));
            extras.add(extraService.findExtraByNameEn("Disco"));
            extras.add(extraService.findExtraByNameEn("Sauna"));
            extras.add(extraService.findExtraByNameEn("Gym"));
        }
        catch (NoEntityFoundException e){
            assert(false);
            return;
        }

        boolean result = shipService.saveShip(Ship.builder()
                .name("Nordkapp")
                .capacity(450)
                .routeNameEn("Three northern capitals").routeNameUkr("Три північні столиці")
                .oneTripDurationDays(12)
                .visitingPorts(new HashSet<>(Arrays.asList(port1, port2, port3)))
                .extras(extras)
                .build());
        assert (result);
    }

    private void addCostaMediterranea(){
        Seaport port1, port2, port3;
        try {
            port1 = seaportService.findPortByNameEn("Marseille");
            port2 = seaportService.findPortByNameEn("Nice");
            port3 = seaportService.findPortByNameEn("Monte-Carlo");
        }
        catch (NoEntityFoundException e){
            assert(false);
            return;
        }

        Set<Extra> extras = new HashSet<>();
        try {
            extras.add(extraService.findExtraByNameEn("Theater"));
            extras.add(extraService.findExtraByNameEn("Disco"));
            extras.add(extraService.findExtraByNameEn("Casino"));
            extras.add(extraService.findExtraByNameEn("Beauty salon"));
            extras.add(extraService.findExtraByNameEn("SPA"));
            extras.add(extraService.findExtraByNameEn("Swimming pool"));
            extras.add(extraService.findExtraByNameEn("Water slide"));
            extras.add(extraService.findExtraByNameEn("Children room"));
            extras.add(extraService.findExtraByNameEn("Children's pool"));
        }
        catch (NoEntityFoundException e){
            assert(false);
            return;
        }

        boolean result = shipService.saveShip(Ship.builder()
                .name("Costa Mediterranea")
                .capacity(2200)
                .routeNameEn("South coast of France plus Monaco").routeNameUkr("Південне узбережжя Франції плюс Монако")
                .oneTripDurationDays(9)
                .visitingPorts(new HashSet<>(Arrays.asList(port1, port2, port3)))
                .extras(extras)
                .build());
        assert (result);
    }

    private void addMonarch(){
        Seaport port1, port2, port3;
        try {
            port1 = seaportService.findPortByNameEn("Marseille");
            port2 = seaportService.findPortByNameEn("Nice");
            port3 = seaportService.findPortByNameEn("Monte-Carlo");
        }
        catch (NoEntityFoundException e){
            assert(false);
            return;
        }

        Set<Extra> extras = new HashSet<>();
        try {
            extras.add(extraService.findExtraByNameEn("Disco"));
            extras.add(extraService.findExtraByNameEn("Casino"));
            extras.add(extraService.findExtraByNameEn("Beauty salon"));
            extras.add(extraService.findExtraByNameEn("Swimming pool"));
            extras.add(extraService.findExtraByNameEn("Surf simulator"));
            extras.add(extraService.findExtraByNameEn("Water slide"));
            extras.add(extraService.findExtraByNameEn("Children room"));
            extras.add(extraService.findExtraByNameEn("Children's pool"));
        }
        catch (NoEntityFoundException e){
            assert(false);
            return;
        }

        boolean result = shipService.saveShip(Ship.builder()
                .name("Monarch")
                .capacity(2280)
                .routeNameEn("South coast of France plus Monaco").routeNameUkr("Південне узбережжя Франції плюс Монако")
                .oneTripDurationDays(7)
                .visitingPorts(new HashSet<>(Arrays.asList(port1, port2, port3)))
                .extras(extras)
                .build());
        assert (result);
    }
}
