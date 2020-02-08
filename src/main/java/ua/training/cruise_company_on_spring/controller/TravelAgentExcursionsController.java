package ua.training.cruise_company_on_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.training.cruise_company_on_spring.entity.Excursion;
import ua.training.cruise_company_on_spring.service.ExcursionService;
import ua.training.cruise_company_on_spring.service.NoEntityFoundException;
import ua.training.cruise_company_on_spring.service.SeaportService;

@Controller
@RequestMapping("/travel_agent")
public class TravelAgentExcursionsController {
    @Autowired
    private ExcursionService excursionService;
    @Autowired
    private SeaportService seaportService;

    @GetMapping("/excursions")
    public String getExcursionsList(@RequestParam(required = false) Long seaportId,
                                    Model model) {
        if(seaportId != null){
            model.addAttribute("all_excursions", excursionService.allExcursionsInSeaport(seaportId));
        }
        else {
            model.addAttribute("all_excursions", excursionService.allExcursions());
        }
        model.addAttribute("all_seaports", seaportService.allPorts());
        return "/travel_agent/excursions";
    }

    @GetMapping("/edit_excursion")
    public String showExcursionEditForm(@RequestParam Long excursionId,
                                        Model model){
        try{
            Excursion excursion = excursionService.getExcursionById(excursionId);
            model.addAttribute("excursion", excursion);
        }
        catch (NoEntityFoundException ex){
            model.addAttribute("excursion", new Excursion());
            model.addAttribute("no_excursion_found", true);
        }
        model.addAttribute("all_seaports", seaportService.allPorts());
        return "/travel_agent/edit_excursion";
    }

    @PostMapping("/edit_excursion")
    public String saveUpdatedExcursion(@ModelAttribute Excursion excursion,
                                       @RequestParam Long seaportId) {

        try{
            excursion.setSeaport(seaportService.findPortById(seaportId));
        }
        catch (NoEntityFoundException ex){
            return "redirect:/travel_agent/edit_excursion?excursionId="+ excursion.getId() + "&no_port_found";
        }

        boolean result = excursionService.saveExcursion(excursion);

        //if excursion was not updated
        if ( !result) {
            return "redirect:/travel_agent/edit_excursion?excursionId="+ excursion.getId() + "&error";
        }

        return "redirect:/travel_agent/excursions";
    }


    @GetMapping("/add_excursion")
    public String showExcursionAddForm( Model model){
        model.addAttribute("excursion", new Excursion());
        model.addAttribute("all_seaports", seaportService.allPorts());
        return "/travel_agent/add_excursion";
    }

    @PostMapping("/add_excursion")
    public String saveNewExcursion(@ModelAttribute Excursion excursion,
                                   @RequestParam Long seaportId) {

        try{
            excursion.setSeaport(seaportService.findPortById(seaportId));
        }
        catch (NoEntityFoundException ex){
            return "redirect:/travel_agent/add_excursion?no_port_found";
        }

        boolean result = excursionService.saveExcursion(excursion);

        //if excursion was not added
        if ( !result) {
            return "redirect:/travel_agent/add_excursion?error";
        }

        return "redirect:/travel_agent/excursions";
    }

    @PostMapping("/delete_excursion")
    public String deleteExcursion(@RequestParam Long excursionId) {
        excursionService.deleteExcursion(excursionId);
        return "redirect:/travel_agent/excursions";
    }
}
