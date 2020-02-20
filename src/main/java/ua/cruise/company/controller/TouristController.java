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
    public String getAllCruisesList(Model model,
                                    @PageableDefault(size = DEFAULT_PAGE_SIZE) Pageable pageable) {
        Page<CruiseDTO> cruisesPage = cruiseService.allCruisesFromTodayPaginated(pageable.previousOrFirst());
        model.addAttribute("cruises", cruisesPage);
        return "/tourist/cruises";
    }

    @GetMapping("/order/cruise/{cruise}")
    public String showCruiseOrderForm(@PathVariable Cruise cruise, Model model) {
        model.addAttribute("cruise", CruiseDTOConverter.convertToDTO(cruise));
        return "/tourist/order_cruise";
    }

    @PostMapping("/order/cruise/{cruiseId}")
    public String showCruiseOrderForm(@AuthenticationPrincipal User user,
                                      @PathVariable Long cruiseId,
                                      @RequestParam int quantity,
                                      Model model) {
        try {
            touristOrderService.bookCruise(user, cruiseId, quantity);
            return "redirect:/tourist/my_orders";
        } catch (TransactionSystemException transactionEx) {
            return "redirect:/tourist/order/cruise/" + cruiseId + "?error";
        } catch (Exception ex) {
            return "redirect:/tourist/order/cruise/" + cruiseId + "?exception=" + ex.getClass();
        }
    }

    @GetMapping("/my_orders")
    public String getUserOrders(@AuthenticationPrincipal User user,
                                Model model,
                                @PageableDefault(size = DEFAULT_PAGE_SIZE) Pageable pageable) {
        model.addAttribute("orders", touristOrderService.allOrdersOfUser(user.getId(), pageable.previousOrFirst()));
        return "/tourist/my_orders";
    }

    @GetMapping("/cancel_order/{orderId}")
    public String cancelOrder(@PathVariable Long orderId) {
        try {
            touristOrderService.cancelBooking(orderId);
            return "redirect:/tourist/my_orders";
        } catch (TransactionSystemException transactionEx) {
            return "redirect:/tourist/my_orders?error";
        } catch (Exception ex) {
            return "redirect:/tourist/my_orders?exception=" + ex.getClass();
        }
    }

    @GetMapping("/pay_order/{orderId}")
    public String payOrder(@PathVariable Long orderId) {
        try {
            touristOrderService.payOrder(orderId);
            return "redirect:/tourist/my_orders";
        } catch (TransactionSystemException transactionEx) {
            return "redirect:/tourist/my_orders?error";
        } catch (Exception ex) {
            return "redirect:/tourist/my_orders?exception=" + ex.getClass();
        }
    }


    @GetMapping("/order/{orderId}/excursions")
    public String showExcursionsForOrder(@PathVariable Long orderId, Model model) {
        try {
            model.addAttribute("excursions", touristOrderService.allExcursionsForCruise(orderId));
            model.addAttribute("orderId", orderId);
            return "/tourist/cruise_excursions";
        } catch (NoEntityFoundException ex) {
            return "redirect:/tourist/my_orders?error";
        }
    }

    @PostMapping("/order/{orderId}/excursions")
    public String addExcursionToOrder(@PathVariable Long orderId,
                                      @RequestParam(value = "chosenExcursions", required = false) List<Long> chosenExcursions) {

        try {
            if (chosenExcursions == null)
                chosenExcursions = new ArrayList<>();
            touristOrderService.addExcursionsToOrder(orderId, chosenExcursions);
            return "redirect:/tourist/my_orders";
        } catch (NoEntityFoundException ex) {
            return "redirect:/tourist/my_orders?error";
        }
    }

    @GetMapping("/print_order/{orderId}")
    public String showPrintPromise(@PathVariable Long orderId) {
        return "/tourist/print_order";
    }
}
