package ua.cruise.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cruise.company.dto.TravelAgentOrderDTO;
import ua.cruise.company.dto.utility.OrderDTOConvertUtils;
import ua.cruise.company.entity.Extra;
import ua.cruise.company.entity.Order;
import ua.cruise.company.entity.OrderStatus;
import ua.cruise.company.repository.ExtraRepository;
import ua.cruise.company.repository.OrderRepository;
import ua.cruise.company.service.exception.NoEntityFoundException;

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
