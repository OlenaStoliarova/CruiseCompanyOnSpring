package ua.cruise.company.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.cruise.company.controller.form.RegistrationForm;
import ua.cruise.company.controller.form.mapper.RegistrationFormMapper;
import ua.cruise.company.entity.User;
import ua.cruise.company.entity.UserRole;
import ua.cruise.company.service.UserService;
import ua.cruise.company.service.exception.NonUniqueObjectException;

import javax.validation.Valid;

@Controller
public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private UserService userService;

    @GetMapping(value = {"/"})
    public String showIndexPage() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new RegistrationForm());
        return "registration";
    }

    @PostMapping("/registration")
    public String submitRegistrationForm(@Valid @ModelAttribute("registration_form") RegistrationForm registrationForm,
                                         BindingResult bindingResult,
                                         Model model) {

        if (hasNoValidationErrors(registrationForm, bindingResult, model)) {
            if (registerNewUser(registrationForm, model))
                return "redirect:/?registration_success=true";
        }

        model.addAttribute("user", registrationForm);
        return "registration";
    }

    @GetMapping(value = {"/main"})
    public String showMainPageForUserRole(Authentication authentication) {
        if (authentication.getAuthorities().contains(UserRole.ROLE_ADMIN))
            return "admin/admin_main";
        if (authentication.getAuthorities().contains(UserRole.ROLE_TRAVEL_AGENT))
            return "travel_agent/travel_agent_main";

        return "tourist/tourist_main";
    }


    private boolean hasNoValidationErrors(RegistrationForm registrationForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors() ||
                !registrationForm.getPassword().equals(registrationForm.getRepeatPassword())) {
            LOGGER.error("Validation error: " + bindingResult.getFieldErrors());
            model.addAttribute("validation_errors", true);
            return false;
        }
        return true;
    }

    private boolean registerNewUser(RegistrationForm registrationForm, Model model) {
        LOGGER.info("registering new User: " + registrationForm);
        User user = new RegistrationFormMapper().mapToEntity(registrationForm);

        try {
            userService.create(user);
            return true;
        } catch (NonUniqueObjectException e) {
            model.addAttribute("non_unique", true);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            model.addAttribute("registration_error", true);
        }
        return false;
    }
}
