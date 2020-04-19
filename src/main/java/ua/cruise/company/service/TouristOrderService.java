package ua.cruise.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cruise.company.dto.ExcursionDTO;
import ua.cruise.company.dto.OrderDTO;
import ua.cruise.company.dto.converter.ExcursionDTOConverter;
import ua.cruise.company.dto.converter.OrderDTOConverter;
import ua.cruise.company.entity.*;
import ua.cruise.company.repository.CruiseRepository;
import ua.cruise.company.repository.ExcursionRepository;
import ua.cruise.company.repository.OrderRepository;
import ua.cruise.company.service.exception.NoEntityFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TouristOrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CruiseRepository cruiseRepository;
    @Autowired
    private ExcursionRepository excursionRepository;

    public Page<OrderDTO> getAllOrdersOfUser(Long userId, Pageable pageable) {
        Page<Order> orders =  orderRepository.findByUser_IdOrderByCreationDateDesc(userId, pageable);
        List<OrderDTO> curPageDTO = orders.getContent().stream()
                .map(OrderDTOConverter::convertToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(curPageDTO, pageable, orders.getTotalElements());
    }

    @Transactional
    public void bookCruise(User tourist, Long cruiseId, int quantity) throws NoEntityFoundException {
        Cruise cruise = cruiseRepository.findById(cruiseId)
                .orElseThrow(() -> new NoEntityFoundException("There is no cruise with provided id (" + cruiseId + ")"));
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

    @Transactional
    public void cancelBooking(Long orderId) throws NoEntityFoundException {
        Order order = getOrderById(orderId);
        order.setStatus(OrderStatus.CANCELED);

        Cruise cruise = order.getCruise();
        cruise.setVacancies(cruise.getVacancies() + order.getQuantity());

        cruiseRepository.save(cruise);
        orderRepository.save(order);
    }

    @Transactional
    public void payOrder(Long orderId) throws NoEntityFoundException {
        Order order = getOrderById(orderId);
        order.setStatus(OrderStatus.PAID);
        orderRepository.save(order);
    }

    public List<ExcursionDTO> getAllExcursionsAvailableForOrder(Long orderId) throws NoEntityFoundException {
        Order order = getOrderById(orderId);
        List<Long> portIds = order.getCruise().getShip().getVisitingPorts().stream()
                .map(Seaport::getId)
                .collect(Collectors.toList());

        List<Excursion> excursions = excursionRepository.findBySeaport_IdIn(portIds);
        return excursions.stream()
                .map(ExcursionDTOConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void addExcursionsToOrder(Long orderId, List<Long> excursionsIds) throws NoEntityFoundException {
        Order order = getOrderById(orderId);
        if( ! excursionsIds.isEmpty()){
            Set<Excursion> excursionsSet = new HashSet<>(excursionRepository.findByIdIn(excursionsIds));
            order.setExcursions(excursionsSet);
        }
        order.setStatus(OrderStatus.EXCURSIONS_ADDED);
        orderRepository.save(order);
    }

    private Order getOrderById(Long orderId) throws NoEntityFoundException {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new NoEntityFoundException("There is no order with provided id (" + orderId + ")"));
    }

}
