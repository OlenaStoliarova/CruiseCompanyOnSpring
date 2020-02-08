package ua.training.cruise_company_on_spring.dto;

import lombok.*;
import ua.training.cruise_company_on_spring.entity.Seaport;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CruiseDTO {
    private Long cruiseId;

    private LocalDate startingDate;

    private String routeNameEn;
    private String routeNameUkr;
    private List<Seaport> portsList;
    private int durationDays;

    private String shipName;
    private int shipCapacity;

    private BigDecimal price;
}
