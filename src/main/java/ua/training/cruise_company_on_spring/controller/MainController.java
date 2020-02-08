package ua.training.cruise_company_on_spring.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.training.cruise_company_on_spring.entity.UserRole;

@Controller
public class MainController {

    @RequestMapping(value = { "/" })
    public String firstPage() {
        return "index";
    }


    @RequestMapping( "/login" )
    public String getLogin(@RequestParam( value = "error", required = false ) String error,
                           @RequestParam( value = "logout", required = false ) String logout,
                           Model model) {
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);
        return "login";
    }


    @RequestMapping(value = { "/main" })
    public String openMain(Authentication authentication) {
        if( authentication.getAuthorities().contains(UserRole.ROLE_ADMIN))
            return "admin/admin_main";
        if( authentication.getAuthorities().contains(UserRole.ROLE_TRAVEL_AGENT))
            return "travel_agent/travel_agent_main";

        return "tourist/tourist_main";
    }

}
