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

@Controller
@RequestMapping("/admin")
public class AdminSeaportController {
    @Autowired
    private SeaportService seaportService;

    @GetMapping("/seaports")
    public String getAllPortsList(Model model) {
        model.addAttribute("all_ports", seaportService.allPorts());
        model.addAttribute("new_port", new Seaport());
        return "/admin/seaports";
    }

    @PostMapping("/addPort")
    public String addSeaport(@ModelAttribute Seaport seaport) {

        boolean result = seaportService.savePort(seaport);

        if (!result) {
            return "redirect:/admin/seaports?error";
        }

        return "redirect:/admin/seaports";
    }
}
