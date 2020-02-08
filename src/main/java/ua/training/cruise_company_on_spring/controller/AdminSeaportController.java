package ua.training.cruise_company_on_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.training.cruise_company_on_spring.entity.Seaport;
import ua.training.cruise_company_on_spring.service.SeaportService;

@Controller
public class AdminSeaportController {
    @Autowired
    private SeaportService seaportService;

    @GetMapping("/admin/seaports")
    public String getAllPortsList(Model model) {
        model.addAttribute("all_ports", seaportService.allPorts());
        model.addAttribute("new_port", new Seaport());
        return "/admin/seaports";
    }

    @PostMapping("/admin/addPort")
    public String addSeaport(@ModelAttribute Seaport seaport){

        boolean result = seaportService.savePort(seaport);

        //if port was not added
        if ( !result) {
            return "redirect:/admin/seaports?error";
        }

        return "redirect:/admin/seaports";
    }
}
