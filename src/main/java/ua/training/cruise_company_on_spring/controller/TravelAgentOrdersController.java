package ua.training.cruise_company_on_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.training.cruise_company_on_spring.entity.Order;
import ua.training.cruise_company_on_spring.service.NoEntityFoundException;
import ua.training.cruise_company_on_spring.service.TravelAgentOrdersService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/travel_agent")
public class TravelAgentOrdersController {
    @Autowired
    private TravelAgentOrdersService travelAgentOrdersService;

    @GetMapping("/orders")
    public String showAllOrdersList(Model model) {
        model.addAttribute("orders", travelAgentOrdersService.allOrders());
        return "/travel_agent/orders";
    }


    @GetMapping("/order/{order}/add_bonuses")
    public String showExcursionsForOrder(@PathVariable Order order, Model model){
        try{
            model.addAttribute( "bonuses", travelAgentOrdersService.allBonusesForCruise(order.getId()));
            model.addAttribute( "orderId", order.getId());
            model.addAttribute( "totalPrice", order.getTotalPrice());
            return "/travel_agent/cruise_bonuses";
        } catch (NoEntityFoundException ex){
            return "redirect:/travel_agent/orders?error";
        }
    }

    @PostMapping("/order/{orderId}/add_bonuses")
    public String addExcursionToOrder(@PathVariable Long orderId,
                                      @RequestParam(value = "chosenBonuses", required = false) List<Long> chosenBonuses){

        try{
            if( chosenBonuses == null)
                chosenBonuses = new ArrayList<>();
            travelAgentOrdersService.addBonusesToOrder(orderId, chosenBonuses);
            return "redirect:/travel_agent/orders";
        } catch (NoEntityFoundException ex){
            return "redirect:/travel_agent/orders?error";
        }
    }
}
