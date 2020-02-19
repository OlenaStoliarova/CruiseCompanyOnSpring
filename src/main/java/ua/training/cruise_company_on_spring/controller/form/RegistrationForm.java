package ua.training.cruise_company_on_spring.controller.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegistrationForm {
    @Pattern(regexp = "^[a-zA-Z0-9]+['a-zA-Z0-9._-]*@[a-zA-Z0-9]+(['a-zA-Z0-9._-])*$", message="Validation failed for email")
    private String email;

    private String password;
    private String repeatPassword;

    @Pattern(regexp = "^[a-zA-Z]+([' -]?[a-zA-Z]+)*$",
             message="Validation failed for first name in English. Only latin letters, apostrophe, hyphen and space are allowed")
    private String firstNameEn;

    @Pattern(regexp = "^[a-zA-Z]+([' -]?[a-zA-Z]+)*$",
            message="Validation failed for last name in English. Only latin letters, apostrophe, hyphen and space are allowed")
    private String lastNameEn;

    @Size(min = 2, message = "The first name in your native language is too short. At least two characters are expected")
    private String firstNameNative;
    @Size(min = 2, message = "The last name in your native language is too short. At least two characters are expected")
    private String lastNameNative;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getFirstNameEn() {
        return firstNameEn;
    }

    public void setFirstNameEn(String firstNameEn) {
        this.firstNameEn = firstNameEn;
    }

    public String getLastNameEn() {
        return lastNameEn;
    }

    public void setLastNameEn(String lastNameEn) {
        this.lastNameEn = lastNameEn;
    }

    public String getFirstNameNative() {
        return firstNameNative;
    }

    public void setFirstNameNative(String firstNameNative) {
        this.firstNameNative = firstNameNative;
    }

    public String getLastNameNative() {
        return lastNameNative;
    }

    public void setLastNameNative(String lastNameNative) {
        this.lastNameNative = lastNameNative;
    }

    @Override
    public String toString() {
        return "RegistrationForm{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                ", firstNameEn='" + firstNameEn + '\'' +
                ", lastNameEn='" + lastNameEn + '\'' +
                ", firstNameNative='" + firstNameNative + '\'' +
                ", lastNameNative='" + lastNameNative + '\'' +
                '}';
    }
}
