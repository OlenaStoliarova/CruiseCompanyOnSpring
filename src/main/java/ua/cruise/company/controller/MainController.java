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
import org.springframework.web.bind.annotation.RequestParam;
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
    public String firstPage() {
        return "index";
    }


    @GetMapping("/login")
    public String getLogin(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model) {
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);
        return "login";
    }

    @GetMapping("/registration")
    public String showRegistartionForm(Model model) {
        model.addAttribute("user", new RegistrationForm());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerNewUser(@Valid @ModelAttribute("registration_form") RegistrationForm registrationForm,
                                  BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors() ||
                !registrationForm.getPassword().equals(registrationForm.getRepeatPassword())) {

            model.addAttribute("validation_errors", true);
            model.addAttribute("user", registrationForm);
            return "registration";
        }

        LOGGER.info("registerNewUser: " + registrationForm);
        User user = new RegistrationFormMapper().mapToEntity(registrationForm);

        try {
            userService.saveUser(user);
        } catch (NonUniqueObjectException e) {
            model.addAttribute("non_unique", true);
            model.addAttribute("user", registrationForm);
            return "registration";
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            model.addAttribute("registration_error", true);
            model.addAttribute("user", registrationForm);
            return "registration";
        }

        return "redirect:/?registration_success=true";
    }


    @GetMapping(value = {"/main"})
    public String openMain(Authentication authentication) {
        if (authentication.getAuthorities().contains(UserRole.ROLE_ADMIN))
            return "admin/admin_main";
        if (authentication.getAuthorities().contains(UserRole.ROLE_TRAVEL_AGENT))
            return "travel_agent/travel_agent_main";

        return "tourist/tourist_main";
    }

}
