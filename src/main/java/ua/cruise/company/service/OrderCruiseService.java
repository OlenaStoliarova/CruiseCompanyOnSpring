package ua.cruise.company.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cruise.company.dto.CruiseDTO;
import ua.cruise.company.dto.converter.CruiseDTOConverter;
import ua.cruise.company.entity.Cruise;
import ua.cruise.company.entity.Order;
import ua.cruise.company.entity.OrderStatus;
import ua.cruise.company.entity.User;
import ua.cruise.company.repository.CruiseRepository;
import ua.cruise.company.repository.OrderRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderCruiseService {
    private static final Logger LOGGER= LoggerFactory.getLogger(OrderCruiseService.class);
    @Autowired
    private CruiseRepository cruiseRepository;
    @Autowired
    private OrderRepository orderRepository;


    public Page<CruiseDTO> allCruisesFromTodayPaginated(Pageable pageable) {
        Page<Cruise> cruises = cruiseRepository.findAllByStartingDateGreaterThanEqualAndVacanciesGreaterThanOrderByStartingDateAsc(LocalDate.now(), 0, pageable);
        List<CruiseDTO> curPageDTO = cruises.getContent().stream()
                .map(CruiseDTOConverter::convertToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(curPageDTO, pageable, cruises.getTotalElements());
    }

    @Transactional
    public void bookCruise(User tourist, Long cruiseId, int quantity){
        Optional<Cruise> cruiseFromDB = cruiseRepository.findById(cruiseId);

        if( cruiseFromDB.isPresent()) {
            Cruise cruise = cruiseFromDB.get();
            cruise.setVacancies(cruise.getVacancies() - quantity);

            Order order = Order.builder()
                    .creationDate(LocalDate.now())
                    .user(tourist)
                    .cruise(cruise)
                    .quantity(quantity)
                    .totalPrice(cruise.getPrice().multiply(BigDecimal.valueOf(quantity)))
                    .status(OrderStatus.NEW)
                    .build();

            cruiseRepository.save(cruise);
            orderRepository.save(order);
        }
    }

    @Transactional
    public void cancelBooking(Long orderId) {
        Optional<Order> orderFromDB = orderRepository.findById(orderId);

        if (orderFromDB.isPresent()) {
            Order order = orderFromDB.get();
            order.setStatus(OrderStatus.CANCELED);

            Cruise cruise = order.getCruise();
            cruise.setVacancies(cruise.getVacancies() + order.getQuantity());

            cruiseRepository.save(cruise);
            orderRepository.save(order);
        }
    }

}
