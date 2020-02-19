package ua.cruise.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.cruise.company.entity.User;
import ua.cruise.company.service.exception.NoEntityFoundException;
import ua.cruise.company.service.TouristOrdersService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tourist")
public class TouristOrderController {
    @Autowired
    private TouristOrdersService touristOrdersService;

    @GetMapping("/my_orders")
    public String getUserOrders(@AuthenticationPrincipal User user,
                                Model model) {
        model.addAttribute("orders", touristOrdersService.allOrdersOfUser(user.getId()));
        return "/tourist/my_orders";
    }


    @GetMapping("/pay_order/{orderId}")
    public String payOrder(@PathVariable Long orderId){
        try {
            touristOrdersService.payOrder(orderId);
            return "redirect:/tourist/my_orders";
        }catch (TransactionSystemException transactionEx){
            return "redirect:/tourist/my_orders?error";
        } catch (Exception ex){
            return "redirect:/tourist/my_orders?exception=" + ex.getClass();
        }
    }


    @GetMapping("/order/{orderId}/excursions")
    public String showExcursionsForOrder(@PathVariable Long orderId, Model model){
        try{
            model.addAttribute( "excursions", touristOrdersService.allExcursionsForCruise(orderId));
            model.addAttribute( "orderId", orderId);
            return "/tourist/cruise_excursions";
        } catch (NoEntityFoundException ex){
            return "redirect:/tourist/my_orders?error";
        }
    }


    @PostMapping("/order/{orderId}/excursions")
    public String addExcursionToOrder(@PathVariable Long orderId,
                                      @RequestParam(value = "chosenExcursions", required = false) List<Long> chosenExcursions){

        try{
            if( chosenExcursions == null)
                chosenExcursions = new ArrayList<>();
            touristOrdersService.addExcursionsToOrder(orderId, chosenExcursions);
            return "redirect:/tourist/my_orders";
        } catch (NoEntityFoundException ex){
            return "redirect:/tourist/my_orders?error";
        }
    }

    //http://localhost:8080/tourist/print_order/9
    @GetMapping ("/print_order/{orderId}")
    @ResponseBody
    public String showPrintPromise(@PathVariable Long orderId){
        return "TO DO: finish this";
    }
}
