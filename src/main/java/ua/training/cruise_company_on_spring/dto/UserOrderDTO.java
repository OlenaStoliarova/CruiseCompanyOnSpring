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
public class UserOrderDTO {
    private Long orderId;
    private LocalDate orderDate;
    private int quantity;
    private String routeNameEn;
    private String routeNameUkr;
    private LocalDate cruiseStartingDate;
    private LocalDate cruiseFinishingDate;

    private String addedExcursionsEn;
    private String addedExcursionsUkr;

    private String freeExtrasEn;
    private String freeExtrasUkr;

    private BigDecimal totalPrice;

    private OrderStatus status;
}
