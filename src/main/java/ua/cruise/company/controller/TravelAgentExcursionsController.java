package ua.cruise.company.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.cruise.company.controller.form.ExcursionForm;
import ua.cruise.company.controller.form.mapper.ExcursionFormMapper;
import ua.cruise.company.entity.Excursion;
import ua.cruise.company.entity.Seaport;
import ua.cruise.company.service.ExcursionService;
import ua.cruise.company.service.SeaportService;
import ua.cruise.company.service.exception.NoEntityFoundException;
import ua.cruise.company.service.exception.NonUniqueObjectException;

import javax.validation.Valid;

@Controller
@RequestMapping("/travel_agent")
public class TravelAgentExcursionsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TravelAgentExcursionsController.class);

    public static final int DEFAULT_PAGE_SIZE_FOR_EXCURSIONS_LIST = 4;

    @Autowired
    private ExcursionService excursionService;
    @Autowired
    private SeaportService seaportService;

    @GetMapping("/excursions")
    public String getExcursionsList(@RequestParam(required = false) Long seaportId,
                                    Model model,
                                    @PageableDefault(size = DEFAULT_PAGE_SIZE_FOR_EXCURSIONS_LIST) Pageable pageable) {
        if (seaportId != null) {
            model.addAttribute("all_excursions", excursionService.allExcursionsInSeaport(seaportId, pageable.previousOrFirst()));
        } else {
            model.addAttribute("all_excursions", excursionService.allExcursions(pageable.previousOrFirst()));
        }
        model.addAttribute("all_seaports", seaportService.allPorts());
        return "/travel_agent/excursions";
    }


    @GetMapping("/edit_excursion")
    public String showExcursionEditForm(@RequestParam Long excursionId,
                                        Model model) {
        Excursion excursion;
        try {
            excursion = excursionService.getExcursionById(excursionId);
        } catch (NoEntityFoundException ex) {
            model.addAttribute("no_excursion_found", true);
            return "/travel_agent/edit_excursion";
        }

        ExcursionForm excursionForm = new ExcursionFormMapper().fillForm(excursion);
        prefillForm(excursionForm, model);
        return "/travel_agent/edit_excursion";
    }

    @PostMapping("/edit_excursion")
    public String saveUpdatedExcursion(@RequestParam Long excursionId,
                                       @Valid @ModelAttribute("excursion") ExcursionForm excursionForm,
                                       BindingResult bindingResult, Model model) {

        LOGGER.info("editing excursion {}, new data {}", excursionId, excursionForm);
        if (bindingResult.hasErrors()) {
            LOGGER.error("Validation error: " + bindingResult.getFieldErrors());
            model.addAttribute("validation_errors", true);
            prefillForm(excursionForm, model);
            return "/travel_agent/edit_excursion";
        }

        Seaport port;
        try {
            port = seaportService.findPortById(excursionForm.getSeaportId());
        } catch (NoEntityFoundException ex) {
            model.addAttribute("no_port_found", true);
            prefillForm(excursionForm, model);
            return "/travel_agent/edit_excursion";
        }

        Excursion excursion = new ExcursionFormMapper().mapToEntity(excursionForm);
        excursion.setId(excursionId);
        excursion.setSeaport(port);

        boolean result;
        try {
            result = excursionService.editExcursion(excursion);
        } catch (NonUniqueObjectException e) {
            LOGGER.info(e.getMessage());
            model.addAttribute("non_unique", true);
            prefillForm(excursionForm, model);
            return "/travel_agent/edit_excursion";
        }

        if (!result) {
            LOGGER.info("saveExcursion error");
            model.addAttribute("error", true);
            prefillForm(excursionForm, model);
            return "/travel_agent/edit_excursion";
        }
        LOGGER.info("excursion was saved after edit");

        return "redirect:/travel_agent/excursions";
    }


    @GetMapping("/add_excursion")
    public String showExcursionAddForm(Model model) {
        prefillForm(new ExcursionForm(), model);
        return "/travel_agent/add_excursion";
    }

    @PostMapping("/add_excursion")
    public String saveNewExcursion(@Valid @ModelAttribute("excursion") ExcursionForm excursionForm,
                                   BindingResult bindingResult, Model model) {

        LOGGER.info("adding excursion: " + excursionForm);
        if (bindingResult.hasErrors()) {
            LOGGER.error("Validation error: " + bindingResult.getFieldErrors());
            model.addAttribute("validation_errors", true);
            prefillForm(excursionForm, model);
            return "/travel_agent/add_excursion";
        }

        Seaport port;
        try {
            port = seaportService.findPortById(excursionForm.getSeaportId());
        } catch (NoEntityFoundException ex) {
            model.addAttribute("no_port_found", true);
            prefillForm(excursionForm, model);
            return "/travel_agent/add_excursion";
        }

        Excursion excursion = new ExcursionFormMapper().mapToEntity(excursionForm);
        excursion.setSeaport(port);

        boolean result;
        try {
            result = excursionService.createExcursion(excursion);
        } catch (NonUniqueObjectException e) {
            LOGGER.info(e.getMessage());
            model.addAttribute("non_unique", true);
            prefillForm(excursionForm, model);
            return "/travel_agent/add_excursion";
        }

        if (!result) {
            LOGGER.info("saveExcursion error");
            model.addAttribute("error", true);
            prefillForm(excursionForm, model);
            return "/travel_agent/add_excursion";
        }
        LOGGER.info("excursion was added");

        return "redirect:/travel_agent/excursions";
    }

    @PostMapping("/delete_excursion")
    public String deleteExcursion(@RequestParam Long excursionId) {
        excursionService.deleteExcursion(excursionId);
        return "redirect:/travel_agent/excursions";
    }


    private void prefillForm(ExcursionForm excursionForm, Model model) {
        model.addAttribute("excursion", excursionForm);
        model.addAttribute("all_seaports", seaportService.allPorts());
    }
}
