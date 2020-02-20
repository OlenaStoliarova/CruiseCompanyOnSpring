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
import ua.cruise.company.service.OrderCruiseService;

@Controller
@RequestMapping("/tourist")
public class TouristCruiseController {
    public static final int DEFAULT_PAGE_SIZE_FOR_CRUISE_LIST = 6;

    @Autowired
    private OrderCruiseService orderCruiseService;

    @GetMapping("/cruises")
    public String getAllPortsList(Model model,
                                  @PageableDefault(size = DEFAULT_PAGE_SIZE_FOR_CRUISE_LIST) Pageable pageable)  {
        Page<CruiseDTO> cruisesPage = orderCruiseService.allCruisesFromTodayPaginated( pageable.previousOrFirst());
        model.addAttribute("cruises", cruisesPage);
        return "/tourist/cruises";
    }


    @GetMapping("/order/cruise/{cruise}")
    public String showCruiseOrderForm(@PathVariable Cruise cruise, Model model){
        model.addAttribute("cruise", CruiseDTOConverter.convertToDTO(cruise));
        return "/tourist/order_cruise";
    }

    @PostMapping("/order/cruise/{cruiseId}")
    public String showCruiseOrderForm(@AuthenticationPrincipal User user,
                                      @PathVariable Long cruiseId,
                                      @RequestParam int quantity,
                                      Model model){
        try {
            orderCruiseService.bookCruise(user, cruiseId, quantity);
            return "redirect:/tourist/my_orders";
        }catch (TransactionSystemException transactionEx){
            return "redirect:/tourist/order/cruise/" + cruiseId + "?error";
        } catch (Exception ex){
            return "redirect:/tourist/order/cruise/" + cruiseId + "?exception=" + ex.getClass();
        }
    }


    @GetMapping("/cancel_order/{orderId}")
    public String cancelOrder(@PathVariable Long orderId){
        try {
            orderCruiseService.cancelBooking(orderId);
            return "redirect:/tourist/my_orders";
        }catch (TransactionSystemException transactionEx){
            return "redirect:/tourist/my_orders?error";
        } catch (Exception ex){
            return "redirect:/tourist/my_orders?exception=" + ex.getClass();
        }
    }
}
