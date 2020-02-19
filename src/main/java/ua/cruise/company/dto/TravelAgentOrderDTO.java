package ua.cruise.company.dto;

import lombok.*;
import ua.cruise.company.entity.OrderStatus;

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
