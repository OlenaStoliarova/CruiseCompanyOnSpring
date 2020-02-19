package ua.cruise.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.cruise.company.entity.User;
import ua.cruise.company.entity.UserRole;
import ua.cruise.company.service.exception.NoEntityFoundException;
import ua.cruise.company.service.UserService;

import java.util.Arrays;

@Controller
public class AdminUsersController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin/users")
    public String userList(@AuthenticationPrincipal User user,
                           @RequestParam( value = "error_updating_role", required = false ) String error_updating_role,
                           Model model) {
        model.addAttribute("error_updating_role", error_updating_role != null);
        model.addAttribute("current_user", user.getEmail());
        model.addAttribute("allUsers", userService.allUsers());
        model.addAttribute("roles", Arrays.asList(UserRole.values()) );
        return "admin/users";
    }

    @PostMapping("/admin/updateUserRole")
    public String updateUserRole(@RequestParam String email,
                                 @RequestParam String userRoles){

        try {
            userService.updateUserRole(email, UserRole.valueOf(userRoles));
        }catch (NoEntityFoundException ex){
            return "redirect:/admin/users?error_updating_role";
        }
        return "redirect:/admin/users";
    }
}
