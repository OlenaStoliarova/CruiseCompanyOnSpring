package ua.cruise.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.cruise.company.dto.CruiseDTO;
import ua.cruise.company.dto.converter.CruiseDTOConverter;
import ua.cruise.company.entity.Cruise;
import ua.cruise.company.entity.User;
import ua.cruise.company.service.CruiseService;
import ua.cruise.company.service.TouristOrderService;
import ua.cruise.company.service.exception.NoEntityFoundException;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tourist")
public class TouristController {
    public static final int DEFAULT_PAGE_SIZE = 5;

    @Autowired
    private TouristOrderService touristOrderService;
    @Autowired
    private CruiseService cruiseService;

    @GetMapping("/cruises")
    public String showAllCruisesList(Model model,
                                     @PageableDefault(size = DEFAULT_PAGE_SIZE) Pageable pageable) {
        Page<CruiseDTO> cruisesPage = cruiseService.getAllCruisesFromToday(pageable.previousOrFirst());
        model.addAttribute("cruises", cruisesPage);
        return "/tourist/cruises";
    }

    @GetMapping("/cruises/{cruise}/order")
    public String showOrderCruiseForm(@PathVariable Cruise cruise, Model model) {
        model.addAttribute("cruise", CruiseDTOConverter.convertToDTO(cruise));
        return "/tourist/order_cruise";
    }

    @PostMapping("/cruises/{cruiseId}/order")
    public String submitOrderCruiseForm(@AuthenticationPrincipal User user,
                                        @PathVariable Long cruiseId,
                                        @RequestParam int quantity,
                                        Model model) {
        try {
            touristOrderService.bookCruise(user, cruiseId, quantity);
            return "redirect:/tourist/orders";
        } catch (TransactionSystemException transactionEx) {
            return "redirect:/tourist/cruises/" + cruiseId + "/order?error";
        } catch (Exception ex) {
            return "redirect:/tourist/cruises/" + cruiseId + "/order?exception=" + ex.getClass();
        }
    }

    @GetMapping("/orders")
    public String showUserOrders(@AuthenticationPrincipal User user,
                                 Model model,
                                 @PageableDefault(size = DEFAULT_PAGE_SIZE) Pageable pageable) {
        model.addAttribute("orders", touristOrderService.getAllOrdersOfUser(user.getId(), pageable.previousOrFirst()));
        return "/tourist/my_orders";
    }

    @GetMapping("/orders/{orderId}/cancel")
    public String submitOrderCancelation(@PathVariable Long orderId) {
        try {
            touristOrderService.cancelBooking(orderId);
            return "redirect:/tourist/orders";
        } catch (TransactionSystemException transactionEx) {
            return "redirect:/tourist/orders?error";
        } catch (Exception ex) {
            return "redirect:/tourist/orders?exception=" + ex.getClass();
        }
    }

    @GetMapping("/orders/{orderId}/pay")
    public String submitOrderPayment(@PathVariable Long orderId) {
        try {
            touristOrderService.payOrder(orderId);
            return "redirect:/tourist/orders";
        } catch (TransactionSystemException transactionEx) {
            return "redirect:/tourist/orders?error";
        } catch (Exception ex) {
            return "redirect:/tourist/orders?exception=" + ex.getClass();
        }
    }


    @GetMapping("/orders/{orderId}/excursions")
    public String showExcursionsForOrder(@PathVariable Long orderId, Model model) {
        try {
            model.addAttribute("excursions", touristOrderService.getAllExcursionsAvailableForOrder(orderId));
            model.addAttribute("orderId", orderId);
            return "/tourist/cruise_excursions";
        } catch (NoEntityFoundException ex) {
            return "redirect:/tourist/orders?error";
        }
    }

    @PostMapping("/orders/{orderId}/excursions")
    public String submitExcursionsToAddToOrder(@PathVariable Long orderId,
                                               @RequestParam(value = "chosenExcursions", required = false) List<Long> chosenExcursions) {

        try {
            if (chosenExcursions == null)
                chosenExcursions = new ArrayList<>();
            touristOrderService.addExcursionsToOrder(orderId, chosenExcursions);
            return "redirect:/tourist/orders";
        } catch (NoEntityFoundException ex) {
            return "redirect:/tourist/orders?error";
        }
    }

    @GetMapping("/orders/{orderId}/print")
    public String showPrintPromise(@PathVariable Long orderId) {
        return "/tourist/print_order";
    }
}
