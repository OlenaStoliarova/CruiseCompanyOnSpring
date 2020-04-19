package ua.cruise.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.cruise.company.entity.Order;
import ua.cruise.company.service.TravelAgentOrderService;
import ua.cruise.company.service.exception.NoEntityFoundException;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/travel_agent")
public class TravelAgentOrdersController {
    public static final int DEFAULT_PAGE_SIZE = 5;

    @Autowired
    private TravelAgentOrderService travelAgentOrderService;

    @GetMapping("/orders")
    public String showAllOrdersList(Model model,
                                    @PageableDefault(size = DEFAULT_PAGE_SIZE) Pageable pageable) {
        model.addAttribute("orders", travelAgentOrderService.getAllOrders(pageable.previousOrFirst()));
        return "/travel_agent/orders";
    }


    @GetMapping("/orders/{order}/bonuses")
    public String showExtrasAvailableForOrder(@PathVariable Order order, Model model) {
        try {
            model.addAttribute("bonuses", travelAgentOrderService.getAllBonusesAvailableForOrder(order.getId()));
            model.addAttribute("orderId", order.getId());
            model.addAttribute("totalPrice", order.getTotalPrice());
            return "/travel_agent/cruise_bonuses";
        } catch (NoEntityFoundException ex) {
            return "redirect:/travel_agent/orders?error";
        }
    }

    @PostMapping("/orders/{orderId}/bonuses")
    public String submitExtrasAddedToOrder(@PathVariable Long orderId,
                                           @RequestParam(value = "chosenBonuses", required = false) List<Long> chosenBonuses) {
        try {
            if (chosenBonuses == null)
                chosenBonuses = new ArrayList<>();
            travelAgentOrderService.addBonusesToOrder(orderId, chosenBonuses);
            return "redirect:/travel_agent/orders";
        } catch (NoEntityFoundException ex) {
            return "redirect:/travel_agent/orders?error";
        }
    }
}
