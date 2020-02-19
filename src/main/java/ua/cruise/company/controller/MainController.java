package ua.cruise.company.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.cruise.company.controller.form.RegistrationForm;
import ua.cruise.company.entity.User;
import ua.cruise.company.entity.UserRole;
import ua.cruise.company.service.exception.NonUniqueObjectException;
import ua.cruise.company.service.UserService;

import javax.validation.Valid;

@Controller
public class MainController {
    private static final Logger LOGGER= LoggerFactory.getLogger(MainController.class);

    @Autowired
    private UserService userService;

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

    @RequestMapping( "/registration" )
    public String showRegistartionForm(Model model){
        model.addAttribute("user", new RegistrationForm());
        return "registration";
    }

    @PostMapping( "/registration" )
    public String registerNewUser(@Valid @ModelAttribute("registration_form") RegistrationForm registrationForm,
                                  BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors() ||
                !registrationForm.getPassword().equals(registrationForm.getRepeatPassword()) ){

            model.addAttribute("validation_errors", true);
            model.addAttribute("user", registrationForm);
            return "registration";
        }

        LOGGER.info("registerNewUser: " + registrationForm);
        User user = User.builder()
                .email(registrationForm.getEmail())
                .password(registrationForm.getPassword())
                .firstNameEn(registrationForm.getFirstNameEn())
                .lastNameEn(registrationForm.getLastNameEn())
                .firstNameNative(registrationForm.getFirstNameNative())
                .lastNameNative(registrationForm.getLastNameNative())
                .build();

        try{
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


    @RequestMapping(value = { "/main" })
    public String openMain(Authentication authentication) {
        if( authentication.getAuthorities().contains(UserRole.ROLE_ADMIN))
            return "admin/admin_main";
        if( authentication.getAuthorities().contains(UserRole.ROLE_TRAVEL_AGENT))
            return "travel_agent/travel_agent_main";

        return "tourist/tourist_main";
    }

}
