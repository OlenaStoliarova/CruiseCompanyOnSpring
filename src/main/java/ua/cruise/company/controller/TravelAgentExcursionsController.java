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
import ua.cruise.company.service.ExcursionService;
import ua.cruise.company.service.SeaportService;
import ua.cruise.company.service.exception.NoEntityFoundException;
import ua.cruise.company.service.exception.NonUniqueObjectException;
import ua.cruise.company.service.exception.SomethingWentWrongException;

import javax.validation.Valid;

@Controller
@RequestMapping("/travel_agent/excursions")
public class TravelAgentExcursionsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TravelAgentExcursionsController.class);

    public static final int DEFAULT_PAGE_SIZE_FOR_EXCURSIONS_LIST = 4;

    @Autowired
    private ExcursionService excursionService;
    @Autowired
    private SeaportService seaportService;

    @GetMapping
    public String showAllExcursionsList(@RequestParam(required = false) Long seaportId,
                                        Model model,
                                        @PageableDefault(size = DEFAULT_PAGE_SIZE_FOR_EXCURSIONS_LIST) Pageable pageable) {
        if (seaportId != null) {
            model.addAttribute("all_excursions", excursionService.getAllExcursionsInSeaport(seaportId, pageable.previousOrFirst()));
        } else {
            model.addAttribute("all_excursions", excursionService.getAllExcursions(pageable.previousOrFirst()));
        }
        model.addAttribute("all_seaports", seaportService.getAllPorts());
        return "/travel_agent/excursions";
    }


    @GetMapping("/{excursionId}/edit")
    public String showEditExcursionForm(@PathVariable Long excursionId,
                                        Model model) {
        model.addAttribute("excursionId", excursionId);
        Excursion excursion;
        try {
            excursion = excursionService.getExcursionById(excursionId);
        } catch (NoEntityFoundException ex) {
            model.addAttribute("no_excursion_found", true);
            return "/travel_agent/edit_excursion";
        }

        ExcursionForm excursionForm = new ExcursionFormMapper().fillForm(excursion);
        setModelAttributesForExcursionForm(model, excursionForm);
        return "/travel_agent/edit_excursion";
    }

    @PostMapping("/{excursionId}/edit")
    public String submitEditExcursionForm(@PathVariable Long excursionId,
                                          @Valid @ModelAttribute("excursion") ExcursionForm excursionForm,
                                          BindingResult bindingResult,
                                          Model model) {

        LOGGER.info("editing excursion {}, new data {}", excursionId, excursionForm);
        if (bindingResult.hasErrors()) {
            LOGGER.error("Validation error: " + bindingResult.getFieldErrors());
            model.addAttribute("validation_errors", true);
            model.addAttribute("excursionId", excursionId);
            setModelAttributesForExcursionForm(model, excursionForm);
            return "/travel_agent/edit_excursion";
        }

        Excursion excursion = new ExcursionFormMapper().mapToEntity(excursionForm);
        excursion.setId(excursionId);

        try {
            Long portId = excursionForm.getSeaportId();
            excursion.setSeaport(seaportService.getPortById(portId));
            excursionService.edit(excursion);
            return "redirect:/travel_agent/excursions?seaportId=" + portId;

        } catch (NoEntityFoundException e) {
            LOGGER.error(e.getMessage());
            model.addAttribute("no_port_found", true);
        } catch (NonUniqueObjectException e) {
            LOGGER.error(e.getMessage());
            model.addAttribute("non_unique", true);
        } catch (SomethingWentWrongException e) {
            LOGGER.error(e.getMessage());
            model.addAttribute("error", true);
        }

        setModelAttributesForExcursionForm(model, excursionForm);
        model.addAttribute("excursionId", excursionId);
        return "/travel_agent/edit_excursion";
    }


    @GetMapping("/add")
    public String showAddExcursionForm(Model model) {
        setModelAttributesForExcursionForm(model, new ExcursionForm());
        return "/travel_agent/add_excursion";
    }


    @PostMapping("/add")
    public String submitAddExcursionForm(@Valid @ModelAttribute("excursion") ExcursionForm excursionForm,
                                         BindingResult bindingResult, Model model) {

        LOGGER.info("adding excursion: " + excursionForm);
        if (bindingResult.hasErrors()) {
            LOGGER.error("Validation error: " + bindingResult.getFieldErrors());
            model.addAttribute("validation_errors", true);
            setModelAttributesForExcursionForm(model, excursionForm);
            return "/travel_agent/add_excursion";
        }

        Excursion excursion = new ExcursionFormMapper().mapToEntity(excursionForm);

        try {
            Long portId = excursionForm.getSeaportId();
            excursion.setSeaport(seaportService.getPortById(portId));
            excursionService.create(excursion);
            return "redirect:/travel_agent/excursions?seaportId=" + portId;

        } catch (NoEntityFoundException e) {
            LOGGER.error(e.getMessage());
            model.addAttribute("no_port_found", true);
        } catch (NonUniqueObjectException e) {
            LOGGER.error(e.getMessage());
            model.addAttribute("non_unique", true);
        } catch (SomethingWentWrongException e) {
            LOGGER.error(e.getMessage());
            model.addAttribute("error", true);
        }

        setModelAttributesForExcursionForm(model, excursionForm);
        return "/travel_agent/add_excursion";
    }

    @PostMapping("/{excursionId}/delete")
    public String deleteExcursion(@PathVariable Long excursionId) {
        excursionService.deleteExcursion(excursionId);
        return "redirect:/travel_agent/excursions";
    }


    private void setModelAttributesForExcursionForm(Model model, ExcursionForm excursionForm) {
        model.addAttribute("excursion", excursionForm);
        model.addAttribute("all_seaports", seaportService.getAllPorts());
    }
}
