package ua.training.cruise_company_on_spring.service;

public class NonUniqueObjectException extends Exception {
    public NonUniqueObjectException(String message) {
        super(message);
    }
}
