package ua.cruise.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.cruise.company.entity.Seaport;
import ua.cruise.company.service.SeaportService;
import ua.cruise.company.service.exception.NonUniqueObjectException;
import ua.cruise.company.service.exception.SomethingWentWrongException;

@Controller
@RequestMapping("/admin")
public class AdminSeaportController {
    @Autowired
    private SeaportService seaportService;

    @GetMapping("/seaports")
    public String showAllPortsPage(Model model) {
        model.addAttribute("all_ports", seaportService.getAllPorts());
        model.addAttribute("new_port", new Seaport());
        return "/admin/seaports";
    }

    @PostMapping("/seaports/add")
    public String submitAddPortForm(@ModelAttribute Seaport seaport) {
        try {
            seaportService.create(seaport);
            return "redirect:/admin/seaports";

        } catch (NonUniqueObjectException | SomethingWentWrongException e) {
            return "redirect:/admin/seaports?error";
        }
    }
}
