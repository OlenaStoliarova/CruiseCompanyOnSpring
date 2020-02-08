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
public class CruiseOrderFormDTO {
    private Long cruiseId;
    private String routeNameEn;
    private String routeNameUkr;
    private List<Seaport> portsList;
    private LocalDate startingDate;
    private LocalDate finishingDate;
    private String shipName;
    private BigDecimal price;
}
