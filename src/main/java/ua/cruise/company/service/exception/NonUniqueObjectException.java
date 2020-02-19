package ua.cruise.company.service.exception;

public class NonUniqueObjectException extends Exception {
    public NonUniqueObjectException(String message) {
        super(message);
    }
}
