package ua.training.cruise_company_on_spring.dto;

import lombok.*;
import ua.training.cruise_company_on_spring.entity.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TravelAgentOrderDTO {
    private Long orderId;
    private String clientEmail;
    private LocalDate orderDate;
    private int quantity;
    private String shipName;
    private LocalDate cruiseStartingDate;

    private String addedExcursionsEn;
    private String addedExcursionsUkr;

    private String freeExtrasEn;
    private String freeExtrasUkr;

    private BigDecimal totalPrice;

    private OrderStatus status;
}
