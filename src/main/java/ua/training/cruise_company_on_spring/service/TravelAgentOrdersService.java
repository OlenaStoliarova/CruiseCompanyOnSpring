package ua.training.cruise_company_on_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.cruise_company_on_spring.dto.TravelAgentOrderDTO;
import ua.training.cruise_company_on_spring.dto.utility.OrderDTOConvertUtils;
import ua.training.cruise_company_on_spring.entity.Extra;
import ua.training.cruise_company_on_spring.entity.Order;
import ua.training.cruise_company_on_spring.entity.OrderStatus;
import ua.training.cruise_company_on_spring.repository.ExtraRepository;
import ua.training.cruise_company_on_spring.repository.OrderRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TravelAgentOrdersService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ExtraRepository extraRepository;


    public List<TravelAgentOrderDTO> allOrders() {
        return orderRepository.findAllByOrderByCreationDateDesc().stream().map(TravelAgentOrdersService::orderToDTO).collect(Collectors.toList());
    }

    public List<Extra> allBonusesForCruise(Long orderId) throws NoEntityFoundException {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NoEntityFoundException("There is no order with provided id (" + orderId + ")"));
        return new ArrayList<>(order.getCruise().getShip().getExtras());
    }

    @Transactional
    public void addBonusesToOrder(Long orderId, List<Long> bonusesIds) throws NoEntityFoundException {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NoEntityFoundException("There is no order with provided id (" + orderId + ")"));
        if( ! bonusesIds.isEmpty()){
            Set<Extra> extras = new HashSet<>( extraRepository.findAllById(bonusesIds));
            order.setFreeExtras(extras);
        }
        order.setStatus(OrderStatus.EXTRAS_ADDED);
        orderRepository.save(order);
    }


    static TravelAgentOrderDTO orderToDTO(Order order){
        return TravelAgentOrderDTO.builder()
                .orderId(order.getId())
                .clientEmail( order.getUser().getEmail())
                .orderDate(order.getCreationDate())
                .quantity(order.getQuantity())
                .shipName( order.getCruise().getShip().getName())
                .cruiseStartingDate(order.getCruise().getStartingDate())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .addedExcursionsEn(OrderDTOConvertUtils.convertOrderExcursionsToString(order, "En"))
                .addedExcursionsUkr(OrderDTOConvertUtils.convertOrderExcursionsToString(order, "Ukr"))
                .freeExtrasEn(OrderDTOConvertUtils.convertOrderFreeExtrasToString(order, "En"))
                .freeExtrasUkr(OrderDTOConvertUtils.convertOrderFreeExtrasToString(order, "Ukr"))
                .build();
    }
}
