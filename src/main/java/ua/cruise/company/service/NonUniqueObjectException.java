package ua.cruise.company.service;

public class NonUniqueObjectException extends Exception {
    public NonUniqueObjectException(String message) {
        super(message);
    }
}
