package ua.cruise.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cruise.company.dto.ExtraDTO;
import ua.cruise.company.dto.OrderDTO;
import ua.cruise.company.dto.converter.ExtraDTOConverter;
import ua.cruise.company.dto.converter.OrderDTOConverter;
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


    public List<OrderDTO> allOrders() {
        return orderRepository.findAllByOrderByCreationDateDesc().stream()
                .map(OrderDTOConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ExtraDTO> allBonusesForCruise(Long orderId) throws NoEntityFoundException {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NoEntityFoundException("There is no order with provided id (" + orderId + ")"));
        return order.getCruise().getShip().getExtras().stream()
                .map(ExtraDTOConverter::convertToDTO)
                .collect(Collectors.toList());
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
}
