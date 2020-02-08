package ua.training.cruise_company_on_spring.service;

public class NoEntityFoundException extends Exception{
    public NoEntityFoundException(String message) {
        super(message);
    }
}
