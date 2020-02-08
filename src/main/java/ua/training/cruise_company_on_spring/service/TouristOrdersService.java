package ua.training.cruise_company_on_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.cruise_company_on_spring.dto.UserOrderDTO;
import ua.training.cruise_company_on_spring.dto.utility.OrderDTOConvertUtils;
import ua.training.cruise_company_on_spring.entity.*;
import ua.training.cruise_company_on_spring.repository.ExcursionRepository;
import ua.training.cruise_company_on_spring.repository.OrderRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TouristOrdersService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ExcursionRepository excursionRepository;

    public List<UserOrderDTO> allOrdersOfUser(Long userId) {
        return orderRepository.findByUser_IdOrderByCreationDateDesc(userId).stream().map(TouristOrdersService::orderToDTO).collect(Collectors.toList());
    }

    @Transactional
    public void payOrder(Long orderId){
        Optional<Order> orderFromDB = orderRepository.findById(orderId);

        if (orderFromDB.isPresent()) {
            Order order = orderFromDB.get();
            order.setStatus(OrderStatus.PAID);
            orderRepository.save(order);
        }
    }

    public List<Excursion> allExcursionsForCruise(Long orderId) throws NoEntityFoundException {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NoEntityFoundException("There is no order with provided id (" + orderId + ")"));
        List<Long> portIds = order.getCruise().getShip().getVisitingPorts().stream().map(Seaport::getId).collect(Collectors.toList());
        return excursionRepository.findBySeaport_IdIn(portIds);
    }

    @Transactional
    public void addExcursionsToOrder(Long orderId, List<Long> excursionsIds) throws NoEntityFoundException {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NoEntityFoundException("There is no order with provided id (" + orderId + ")"));
        if( ! excursionsIds.isEmpty()){
            Set<Excursion> excursionsSet = new HashSet<>(excursionRepository.findByIdIn(excursionsIds));
            order.setExcursions(excursionsSet);
        }
        order.setStatus(OrderStatus.EXCURSIONS_ADDED);
        orderRepository.save(order);
    }


    static UserOrderDTO orderToDTO(Order order){
        return UserOrderDTO.builder()
                .orderId(order.getId())
                .orderDate(order.getCreationDate())
                .routeNameEn(order.getCruise().getShip().getRouteNameEn())
                .routeNameUkr(order.getCruise().getShip().getRouteNameUkr())
                .cruiseStartingDate(order.getCruise().getStartingDate())
                .cruiseFinishingDate( order.getCruise().getStartingDate().plusDays( order.getCruise().getShip().getOneTripDurationDays()))
                .quantity(order.getQuantity())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .addedExcursionsEn(OrderDTOConvertUtils.convertOrderExcursionsToString(order, "En"))
                .addedExcursionsUkr(OrderDTOConvertUtils.convertOrderExcursionsToString(order, "Ukr"))
                .freeExtrasEn(OrderDTOConvertUtils.convertOrderFreeExtrasToString(order, "En"))
                .freeExtrasUkr(OrderDTOConvertUtils.convertOrderFreeExtrasToString(order, "Ukr"))
                .build();
    }

}
