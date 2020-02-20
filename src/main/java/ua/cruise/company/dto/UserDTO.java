package ua.cruise.company.dto;

import ua.cruise.company.entity.UserRole;

public class UserDTO {
    private Long id;
    private String email;
    private String firstNameEn;
    private String lastNameEn;
    private String firstNameNative;
    private String lastNameNative;
    private UserRole role;

    public UserDTO() {
    }

    public UserDTO(Long id, String email, String firstNameEn, String lastNameEn, String firstNameNative, String lastNameNative, UserRole role) {
        this.id = id;
        this.email = email;
        this.firstNameEn = firstNameEn;
        this.lastNameEn = lastNameEn;
        this.firstNameNative = firstNameNative;
        this.lastNameNative = lastNameNative;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstNameEn='" + firstNameEn + '\'' +
                ", lastNameEn='" + lastNameEn + '\'' +
                ", firstNameNative='" + firstNameNative + '\'' +
                ", lastNameNative='" + lastNameNative + '\'' +
                ", role=" + role +
                '}';
    }
}
