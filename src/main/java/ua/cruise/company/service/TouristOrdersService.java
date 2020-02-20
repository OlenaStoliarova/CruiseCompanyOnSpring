package ua.cruise.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cruise.company.dto.ExcursionDTO;
import ua.cruise.company.dto.OrderDTO;
import ua.cruise.company.dto.converter.ExcursionDTOConverter;
import ua.cruise.company.dto.converter.OrderDTOConverter;
import ua.cruise.company.entity.Excursion;
import ua.cruise.company.entity.Order;
import ua.cruise.company.entity.OrderStatus;
import ua.cruise.company.entity.Seaport;
import ua.cruise.company.repository.ExcursionRepository;
import ua.cruise.company.repository.OrderRepository;
import ua.cruise.company.service.exception.NoEntityFoundException;

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

    public List<OrderDTO> allOrdersOfUser(Long userId) {
        return orderRepository.findByUser_IdOrderByCreationDateDesc(userId).stream()
                .map(OrderDTOConverter::convertToDTO)
                .collect(Collectors.toList());
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

    public List<ExcursionDTO> allExcursionsForCruise(Long orderId) throws NoEntityFoundException {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NoEntityFoundException("There is no order with provided id (" + orderId + ")"));
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
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NoEntityFoundException("There is no order with provided id (" + orderId + ")"));
        if( ! excursionsIds.isEmpty()){
            Set<Excursion> excursionsSet = new HashSet<>(excursionRepository.findByIdIn(excursionsIds));
            order.setExcursions(excursionsSet);
        }
        order.setStatus(OrderStatus.EXCURSIONS_ADDED);
        orderRepository.save(order);
    }

}
