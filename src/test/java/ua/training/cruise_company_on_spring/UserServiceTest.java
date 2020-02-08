package ua.training.cruise_company_on_spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.training.cruise_company_on_spring.entity.User;
import ua.training.cruise_company_on_spring.entity.UserRole;
import ua.training.cruise_company_on_spring.service.NoEntityFoundException;
import ua.training.cruise_company_on_spring.service.UserService;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void addNewUser(){
        boolean result = userService.saveUser(User.builder()
                .email("admin@a.a")
                .password("admin")
                .firstNameEn("Olena").lastNameEn("Stoliarova")
                .firstNameNative("Олена").lastNameNative("Столярова")
                .build());
        assert (result);

        try {
            result = userService.updateUserRole("admin@a.a", UserRole.ROLE_ADMIN);
        }catch (NoEntityFoundException ex){}
        assert (result);

        result = userService.saveUser(User.builder()
                .email("misha@b.ua")
                .password("user")
                .firstNameEn("Mykhailo").lastNameEn("Vedmid")
                .firstNameNative("Михайло").lastNameNative("Ведмідь")
                .build());
        assert (result);

        result = userService.saveUser(User.builder()
                .email("a@b.ua")
                .password("user")
                .firstNameEn("Kosya").lastNameEn("Vyhan")
                .firstNameNative("Кося").lastNameNative("Вухань")
                .build());
        assert (result);

        result = userService.saveUser(User.builder()
                .email("boss@c.ua")
                .password("boss")
                .firstNameEn("Maria").lastNameEn("Terehova")
                .firstNameNative("Марія").lastNameNative("Терехова")
                .build());
        assert (result);
    }
}
