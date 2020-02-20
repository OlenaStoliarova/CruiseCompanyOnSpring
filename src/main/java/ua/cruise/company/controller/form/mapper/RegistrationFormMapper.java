package ua.cruise.company.controller.form.mapper;

import ua.cruise.company.controller.form.RegistrationForm;
import ua.cruise.company.entity.User;

public class RegistrationFormMapper implements FormEntityMapper<User, RegistrationForm> {
    @Override
    public User mapToEntity(RegistrationForm registrationForm) {
        return User.builder()
                .email(registrationForm.getEmail())
                .password(registrationForm.getPassword())
                .firstNameEn(registrationForm.getFirstNameEn())
                .lastNameEn(registrationForm.getLastNameEn())
                .firstNameNative(registrationForm.getFirstNameNative())
                .lastNameNative(registrationForm.getLastNameNative())
                .build();
    }
}
